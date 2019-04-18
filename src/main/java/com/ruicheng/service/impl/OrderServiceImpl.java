package com.ruicheng.service.impl;

import com.ruicheng.converter.OrderMainToOrderDTOConverter;
import com.ruicheng.dao.OrderDetailDao;
import com.ruicheng.dao.OrderMainDao;
import com.ruicheng.dto.CartDTO;
import com.ruicheng.dto.OrderDTO;
import com.ruicheng.entity.OrderDetail;
import com.ruicheng.entity.OrderMain;
import com.ruicheng.entity.ProductInfo;
import com.ruicheng.enums.OrderStatusEnum;
import com.ruicheng.enums.PayStatusEnum;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.service.interfaces.OrderService;
import com.ruicheng.service.interfaces.ProductService;
import com.ruicheng.util.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ruicheng
 * on 2019/2/18.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMainDao orderMainDao;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyGenerator.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(0);

        //1. 查询商品数量和价格
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo productInfo =  productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.NO_SUCH_PRODUCT);
            }

            //2. 计算订单总价(每种商品的价格之和)
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库
            orderDetail.setDetailId(KeyGenerator.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailDao.save(orderDetail);

        }


        //3. 写入订单数据库（orderMain和orderDetail）
        OrderMain orderMain = new OrderMain();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMain);
        orderMain.setOrderAmount(orderAmount);
        orderMainDao.save(orderMain);

        //4. 扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
                new CartDTO(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);


        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        Optional<OrderMain> optional = orderMainDao.findById(orderId);
        if (!optional.isPresent()) {
            throw new SellException(ResultEnum.ORDER_IS_NOT_PRESENT);
        }
        OrderMain orderMain = optional.get();

        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDER_DETAIL_IS_NOT_PRESENT);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMain, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMain> orderMainPage = orderMainDao.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMainToOrderDTOConverter.convert(orderMainPage.getContent());

        return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMainPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMain orderMain = new OrderMain();

        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[取消订单] 不是新下的订单，无法完结订单, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCELED.getCode());
        BeanUtils.copyProperties(orderDTO, orderMain);
        OrderMain updateResult = orderMainDao.save(orderMain);
        if (!updateResult.getOrderId().equals(orderMain.getOrderId())) {
            log.error("[取消订单] 更新失败, orderMain={}", orderMain);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单] 订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.EMPTY_ORDER_DETAIL);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //如果已支付, 需要退款
//        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCEED.getCode())) {
//            payService.refund(orderDTO);
//        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[完结订单] 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderDTO, orderMain);
        OrderMain updateResult = orderMainDao.save(orderMain);
        if (!updateResult.getOrderId().equals(orderMain.getOrderId())) {
            log.error("[完结订单] 更新失败, orderMain={}", orderMain);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("[订单支付完成] 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("[订单支付完成] 订单支付状态不正确, orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCEED.getCode());
        OrderMain orderMain = new OrderMain();
        BeanUtils.copyProperties(orderDTO, orderMain);
        OrderMain updateResult = orderMainDao.save(orderMain);
        if (!updateResult.getOrderId().equals(orderMain.getOrderId())) {
            log.error("[订单支付完成] 更新失败, orderMain={}", orderMain);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAILED);
        }

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMain> orderMainPage = orderMainDao.findAll(pageable);

        List<OrderDTO> orderDTOList = OrderMainToOrderDTOConverter.convert(orderMainPage.getContent());

        return new PageImpl<>(orderDTOList, pageable, orderMainPage.getTotalElements());
    }
}

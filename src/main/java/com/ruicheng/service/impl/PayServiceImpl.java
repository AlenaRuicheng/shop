package com.ruicheng.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.ruicheng.dto.OrderDTO;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.service.interfaces.OrderService;
import com.ruicheng.service.interfaces.PayService;
import com.ruicheng.util.JsonFormatterUtil;
import com.ruicheng.util.VerificationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ruicheng
 * on 2019/4/17.
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService{
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    private static final String ORDER_NAME = "微信订单";

    @Override
    public PayResponse create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] 创建订单 payRequest={}", payRequest);

        PayResponse payResponse =  bestPayService.pay(payRequest);
        log.info("[微信支付] 创建订单 payResponse={}", payResponse);
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        // 支付安全措施
        // 1、验证签名
        // 2、验证支付状态
        // 3、验证支付金额
        // 4、验证支付人身份 == 付款人身份

        // 微信支付异步通知(包含签名验证)
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("[微信支付] 异步通知 payResponse={}", JsonFormatterUtil.toJson(payResponse));
        // 修改订单支付状态
        // 1、查找订单(订单是否存在已经验证)
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //2、判断金额是否一致
        if (!VerificationUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderAmount().doubleValue())){
            log.error("[微信支付] 异步通知，订单金额不一致，orderId={}, 微信通知金额:￥{}, 系统金额:￥{}",
            payResponse.getOrderId(), payResponse.getOrderAmount(), orderDTO.getOrderAmount());
            throw new SellException(ResultEnum.WXPAY_MONEY_VERIFICATION_FAILED);
        }
        //3、支付
        orderService.pay(orderDTO);
        return payResponse  ;
    }

    /**
     *  微信退款
     * @param orderDTO
     * @return
     */
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信退款] refundRequest={}", JsonFormatterUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("[微信退款] refundResponse={}", JsonFormatterUtil.toJson(refundRequest));
        return refundResponse;
    }
}

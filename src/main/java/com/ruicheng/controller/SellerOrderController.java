package com.ruicheng.controller;

import com.ruicheng.dto.OrderDTO;
import com.ruicheng.enums.OrderStatusEnum;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.service.interfaces.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家端订单操作
 * Created by Ruicheng
 * on 2019/4/19.
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 显示所有订单
     * @param page  查询第几页数据， 从第一页开始
     * @param pageSize  每页数据个数
     * @return 订单列表视图
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1")Integer page,
                             @RequestParam(value = "pageSize", defaultValue = "6")Integer pageSize,
                             Map<String, Object> model){
        PageRequest request = PageRequest.of(page - 1, pageSize);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        model.put("orderDTOPage", orderDTOPage);
        model.put("currentPage", page);
        model.put("pageSize", pageSize);
        model.put("orderNew", OrderStatusEnum.NEW.getMessage());
        return new ModelAndView("order/list", model);
    }

    /**
     * 取消订单
     * @return  取消订单视图
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId")String orderId,
                               Map<String, Object> model){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
            model.put("redirect_uri", "list");
        }catch (SellException se){
            log.error("[卖家取消订单] 取消失败: {}", se.getMessage());
            model.put("msg", se.getMessage());
            return new ModelAndView("components/error", model);
        }

        model.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        return new ModelAndView("components/succeed");
    }

    /**
     * 订单详情
     * @param orderId 订单ID
     * @param model 向前端传递的数据
     * @return 订单详情视图
     */
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId")String orderId,
                               Map<String, Object> model){
        OrderDTO orderDTO = null;
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException se){
            log.error("[卖家查询订单详情] 查询失败: {}", se.getMessage());
            model.put("msg", se.getMessage());
            model.put("redirect_uri", "list");
            return new ModelAndView("components/error", model);
        }

        model.put("orderDTO", orderDTO);
        model.put("orderNew", OrderStatusEnum.NEW.getMessage());
        return new ModelAndView("order/detail", model);
    }

    /**
     * 卖家完结订单
     * @param orderId   订单ID
     * @param model 向前端传递的数据
     * @return 完结订单视图
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId")String orderId,
                               Map<String, Object> model){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
            model.put("redirect_uri", "list");
        }catch (SellException se){
            log.error("[卖家完结订单] 完结异常: {}", se.getMessage());
            model.put("msg", se.getMessage());
            return new ModelAndView("components/error", model);
        }

        model.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        return new ModelAndView("components/succeed");
    }
}

package com.ruicheng.controller;

import com.lly835.bestpay.model.PayResponse;
import com.ruicheng.dto.OrderDTO;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.service.interfaces.OrderService;
import com.ruicheng.service.interfaces.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Ruicheng
 * on 2019/4/17.
 */
@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId")String orderId,
                               @RequestParam("returnUrl")String returnUrl,
                               Map<String, Object> model){
        //查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null){
            throw new SellException(ResultEnum.ORDER_IS_NOT_PRESENT);
        }

        PayResponse payResponse = payService.create(orderDTO);
        model.put("payResponse", payResponse);
        model.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", model);
    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);

        //返回给微信处理结果，避免微信端不断发出异步通知
        return new ModelAndView("pay/success");
    }
}

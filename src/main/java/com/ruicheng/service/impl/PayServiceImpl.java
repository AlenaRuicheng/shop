package com.ruicheng.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.ruicheng.dto.OrderDTO;
import com.ruicheng.service.interfaces.PayService;
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
    BestPayServiceImpl bestPayService;

    private static final String ORDER_NAME = "微信订单";

    @Override
    public void create(OrderDTO orderDTO) {
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("[微信支付] payRequest={}", payRequest);

        PayResponse payResponse =  bestPayService.pay(payRequest);
        log.info("[微信支付] payResponse={}", payResponse);
    }
}

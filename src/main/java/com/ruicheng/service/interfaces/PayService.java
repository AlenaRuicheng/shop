package com.ruicheng.service.interfaces;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.ruicheng.dto.OrderDTO;

/**
 * Created by Ruicheng
 * on 2019/4/17.
 */
public interface PayService {
    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}

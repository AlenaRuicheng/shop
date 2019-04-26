package com.ruicheng.service.interfaces;

import com.ruicheng.dto.OrderDTO;

/**
 * 消息推送
 * Created by Ruicheng
 * on 2019/4/23.
 */
public interface DeliveryMessage {
    /**
     * 订单状态发生变更时推送消息
     */
    void onOrderStatus(OrderDTO orderDTO);
}

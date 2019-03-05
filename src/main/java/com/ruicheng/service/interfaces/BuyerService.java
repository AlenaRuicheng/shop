package com.ruicheng.service.interfaces;


import com.ruicheng.dto.OrderDTO;

/**
 * 买家服务<br/>
 * Created by Ruicheng
 * on 2019/2/18.
 */
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}

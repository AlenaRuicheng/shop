package com.ruicheng.util;

import com.ruicheng.entity.OrderDetail;

import java.util.List;

/**
 * Created by Ruicheng
 * on 2019/4/23.
 */
public class MessageUtil {
    public static String desplayPurchaseDetails(List<OrderDetail> orderDetailList){
        StringBuilder stringBuilder = new StringBuilder();
        for (OrderDetail orderDetail: orderDetailList){
            stringBuilder.append(orderDetail.getProductName())
                    .append("×")
                    .append(orderDetail.getProductQuantity()).append("  ");
        }
        return stringBuilder.toString();
    }
}

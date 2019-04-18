package com.ruicheng.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruicheng.dto.OrderDTO;
import com.ruicheng.entity.OrderDetail;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
@Slf4j
public class OrderFormToOrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getBuyerName());
        orderDTO.setBuyerPhone(orderForm.getBuyerPhone());
        orderDTO.setBuyerAddress(orderForm.getBuyerAddress());
        orderDTO.setBuyerOpenid(orderForm.getBuyerOpenid());

        List<OrderDetail> orderDetailList;
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            log.error("[对象转换] string不是json格式, string={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAMETER_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}

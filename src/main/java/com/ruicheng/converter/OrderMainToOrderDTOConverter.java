package com.ruicheng.converter;

import com.ruicheng.dto.OrderDTO;
import com.ruicheng.entity.OrderMain;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
public class OrderMainToOrderDTOConverter {

    public static OrderDTO convert(OrderMain orderMain) {

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMain, orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMain> orderMainList) {
        return orderMainList.stream().map(OrderMainToOrderDTOConverter::convert).collect(Collectors.toList());
    }
}

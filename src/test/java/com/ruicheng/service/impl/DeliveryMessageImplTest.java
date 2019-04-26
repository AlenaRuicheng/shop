package com.ruicheng.service.impl;

import com.ruicheng.dto.OrderDTO;
import com.ruicheng.service.interfaces.DeliveryMessage;
import com.ruicheng.service.interfaces.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ruicheng
 * on 2019/4/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryMessageImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    DeliveryMessage deliveryMessage;

    @Test
    public void onOrderStatus() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1555998419031929236");
        deliveryMessage.onOrderStatus(orderDTO);
    }

}
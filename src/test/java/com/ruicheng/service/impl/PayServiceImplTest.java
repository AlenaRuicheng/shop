package com.ruicheng.service.impl;

import com.ruicheng.dto.OrderDTO;
import com.ruicheng.service.interfaces.OrderService;
import com.ruicheng.service.interfaces.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Ruicheng
 * on 2019/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {
    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1555551520486183892");
        payService.create(orderDTO);
    }

}
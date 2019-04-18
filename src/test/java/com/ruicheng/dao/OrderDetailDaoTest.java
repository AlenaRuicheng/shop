package com.ruicheng.dao;

import com.ruicheng.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ruicheng
 * on 2019/2/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail > orderDetailList = orderDetailDao.findByOrderId("651321651651");
        System.out.println(orderDetailList.size());
    }

    @Test
    public void save(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("651321651651");
        orderDetail.setDetailId("132165896513");
        orderDetail.setProductId("12586578");
        orderDetail.setProductName("凉皮");
        orderDetail.setProductIcon("http://xxxxa.jpg");
        orderDetail.setProductPrice(new BigDecimal("6"));
        orderDetail.setProductQuantity(3);

        orderDetailDao.save(orderDetail);
    }

}
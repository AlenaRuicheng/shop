package com.ruicheng.service.impl;

import com.ruicheng.dto.OrderDTO;
import com.ruicheng.entity.OrderDetail;
import com.ruicheng.entity.OrderMain;
import com.ruicheng.service.interfaces.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ruicheng
 * on 2019/2/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    @Transactional
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("方亮");
        orderDTO.setBuyerAddress("前进路");
        orderDTO.setBuyerPhone("14623698526");
        orderDTO.setBuyerOpenid("qijbibiebook20mofn29u3nfi-a");

        //加入购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail od1 = new OrderDetail();
        od1.setProductId("129632");
        od1.setProductQuantity(2);

        OrderDetail od2 = new OrderDetail();
        od2.setProductId("561321");
        od2.setProductQuantity(1);

        orderDetailList.add(od1);
        orderDetailList.add(od2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result + "---------");
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1551842459292547546");
        System.out.println(orderDTO + "--------");
    }

    @Test
    public void findList() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList("3216585", pageRequest);
        System.out.println(orderDTOPage.getContent() + "-------");
    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("5344101550887379230");
        OrderDTO result = orderService.cancel(orderDTO);
        System.out.println(result.getOrderStatus() + "-------");
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("5344101550887379230");
        OrderDTO result = orderService.finish(orderDTO);
        System.out.println(result.getPayStatus() + "-------");
    }

    @Test
    public void pay() throws Exception {
        OrderDTO orderDTO = orderService.findOne("5344101550887379230");
        OrderDTO result = orderService.pay(orderDTO);
        System.out.println(result.getOrderStatus() + "-------");
    }

    @Test
    public void findList2(){
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        System.out.println(orderDTOPage.getContent() + "-------");
    }

}
package com.ruicheng.dao;

import com.ruicheng.entity.OrderMain;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Ruicheng
 * on 2019/2/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMainDaoTest {
    @Autowired
    private OrderMainDao dao;

    @Test
    public void findByBuyerOpenid() throws Exception {

    }

    @Test
    public void save() {
        OrderMain orderMain = new OrderMain();
        orderMain.setOrderId("");
        orderMain.setBuyerName("华琳");
        orderMain.setBuyerAddress("学府路");
        orderMain.setBuyerPhone("0451-2150345");
        orderMain.setBuyerOpenid("15612356");
        orderMain.setOrderAmount(new BigDecimal(11));

        dao.save(orderMain);
    }

}
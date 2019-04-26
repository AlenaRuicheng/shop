package com.ruicheng.dao;

import com.ruicheng.entity.SellerInfo;
import com.ruicheng.util.KeyGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ruicheng
 * on 2019/4/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoDaoTest {

    @Autowired
    SellerInfoDao dao;

    @Test
    public void save(){
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId(KeyGenerator.genUniqueKey());
        sellerInfo.setSellerUsername("admin");
        sellerInfo.setSellerPassword("123");
        sellerInfo.setSellerOpenid("buib12bjfewkj556");

        SellerInfo resul = dao.save(sellerInfo);
        System.err.println(resul);
    }

    @Test
    public void findBySellerOpenid() throws Exception {
        SellerInfo sellerInfo = dao.findBySellerOpenid("buib12bjfewkj556");
        System.err.println(sellerInfo);
    }

}
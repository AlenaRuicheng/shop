package com.ruicheng.service.impl;

import com.ruicheng.entity.SellerInfo;
import com.ruicheng.service.interfaces.SellerService;
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
public class SellerServiceImplTest {

    @Autowired
    SellerService sellerService;

    private static final String sellerOpenid = "buib12bjfewkj556";
    private static final String username = "admin";

    @Test
    public void findSellerInfoBySellerOpenid() throws Exception {
        SellerInfo sellerInfo = sellerService.findSellerInfoBySellerOpenid(sellerOpenid);
        System.err.println(sellerInfo);
    }

    @Test
    public void findSellerInfoBySellerUsernameAndPassword() throws Exception {
        SellerInfo sellerInfo = sellerService.findSellerInfoBySellerUsernameAndPassword("admin", "123");
        System.out.println(sellerInfo);
    }

    @Test
    public void findSellerInfoBySellerUsernameAndOpenid() throws Exception {
        SellerInfo sellerInfo = sellerService.findSellerInfoBySellerUsernameAndOpenid("admin", sellerOpenid);
        System.out.println(sellerInfo);
    }

    @Test
    public void resetPassword(){
        SellerInfo sellerInfo = sellerService.resetPassword(username, sellerOpenid, "123456");
        System.out.println(sellerInfo);
    }
}
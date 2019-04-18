package com.ruicheng.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Ruicheng
 * on 2019/3/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WeChatUserConfigTest {
    @Autowired
    private
    WeChatUserConfig weChatUserConfig;

    @Test
    public void test(){
        System.out.println(weChatUserConfig.getMpAppId() + "\n" + weChatUserConfig.getMpAppSecret());
    }

}
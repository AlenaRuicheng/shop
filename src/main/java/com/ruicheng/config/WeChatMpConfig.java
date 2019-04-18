package com.ruicheng.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Ruicheng
 * on 2019/3/7.
 */
@Component
public class WeChatMpConfig {
    @Autowired
    private WeChatUserConfig weChatUserConfig;

    @Bean
    public WxMpService wxMpService(){
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage(){
        WxMpInMemoryConfigStorage configStorage= new WxMpInMemoryConfigStorage();
        configStorage.setAppId(weChatUserConfig.getMpAppId());
        configStorage.setSecret(weChatUserConfig.getMpAppSecret());
        return configStorage;
    }
}

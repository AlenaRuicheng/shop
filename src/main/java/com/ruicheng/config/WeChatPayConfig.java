package com.ruicheng.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Ruicheng
 * on 2019/4/17.
 */
@Component
public class WeChatPayConfig {
    @Autowired
    private WeChatUserConfig userConfig;

    @Bean
    public BestPayServiceImpl bestPayService(){
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(userConfig.getMpAppId());
        wxPayH5Config.setAppSecret(userConfig.getMpAppSecret());
        wxPayH5Config.setMchId(userConfig.getMchId());
        wxPayH5Config.setMchKey(userConfig.getMchKey());
        wxPayH5Config.setKeyPath(userConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(userConfig.getNotifyUrl());

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);

        return bestPayService;
    }

}

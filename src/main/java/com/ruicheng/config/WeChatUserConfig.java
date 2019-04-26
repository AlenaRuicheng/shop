package com.ruicheng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by Ruicheng
 * on 2019/3/7.
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeChatUserConfig {
    private String mpAppId;
    private String mpAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     *  微信模板ID
     */
    private Map<String, String> templateId;
}

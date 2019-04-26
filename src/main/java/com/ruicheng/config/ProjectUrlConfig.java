package com.ruicheng.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Ruicheng
 * on 2019/4/23.
 */
@Data
@ConfigurationProperties(prefix = "projecturl")
@Component
public class ProjectUrlConfig {

    /** 微信授权URL. */
    private String weChatMpAuthorize;

    /** 项目地址. */
    private String shop;
}

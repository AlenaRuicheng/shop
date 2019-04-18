package com.ruicheng.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Ruicheng
 * on 2019/3/2.
 */
@RestController
@RequestMapping("/wechatest")
@Slf4j
public class WeChatTestController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxfda6eec7a05d746e&secret=60b2e6abc54773ae7fe771d41866e604&code=" + code + "&grant_type=authorization_code";
        //发出http请求
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);
    }
}

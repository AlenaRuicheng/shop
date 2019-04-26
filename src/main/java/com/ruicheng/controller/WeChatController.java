package com.ruicheng.controller;

import com.ruicheng.config.ProjectUrlConfig;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.exceptions.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Ruicheng
 * on 2019/3/7.
 */
@Controller
@RequestMapping("wechat")
@Slf4j
public class WeChatController {
    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl")String returnUrl){
        String url = projectUrlConfig.getWeChatMpAuthorize() + "/shop/wechat/userInfo";
        String redirectUrl;
        try {
            redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
                    WxConsts.OAUTH2_SCOPE_USER_INFO,
                    URLEncoder.encode(returnUrl, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("[微信网页授权] 编码错误： {}", e);
            throw new SellException(ResultEnum.ENCODING_ERROR);
        }
        log.info("redirectUrl==========={}", redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code")String code,
                         @RequestParam("state")String returnedUrl){
        WxMpOAuth2AccessToken accessToken;
        try {
            accessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("[微信网页授权] WxErrorException = {}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openID = accessToken.getOpenId();
        return "redirect:" + returnedUrl + "?openid=" + openID;
    }
}

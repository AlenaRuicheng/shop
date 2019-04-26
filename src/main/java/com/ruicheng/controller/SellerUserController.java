package com.ruicheng.controller;

import com.ruicheng.constant.CookieConstant;
import com.ruicheng.constant.RedisConstant;
import com.ruicheng.entity.SellerInfo;
import com.ruicheng.enums.ResultEnum;
import com.ruicheng.service.interfaces.SellerService;
import com.ruicheng.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 方法名带seller前缀的都将被aspect拦截<br>
 * Created by Ruicheng
 * on 2019/4/22.
 */
@Controller
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    @ResponseBody
    public SellerInfo sellerLogin(HttpServletRequest request,
                            HttpServletResponse response){
        String username = request.getParameter("seller_username");
        String password = request.getParameter("seller_password");
        SellerInfo sellerInfo = sellerService.findSellerInfoBySellerUsernameAndPassword(username, password);

        //设置redis的token
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), sellerInfo.getSellerOpenid(), expire, TimeUnit.SECONDS);

        //设置token到cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.MAX_AGE);

        return sellerInfo;
    }

    @PostMapping("/reset_pwd")
    @ResponseBody
    public Boolean sellerResetPassword(HttpServletRequest request){
        String username = request.getParameter("seller_username");
        String openid = request.getParameter("seller_openid");
        String password = request.getParameter("seller_password");
        return sellerService.resetPassword(username, openid, password) != null;
    }

    @GetMapping("/homepage")
    public ModelAndView toHomepage(){
        return new ModelAndView("seller/homepage");
    }

    @GetMapping("/logout")
    public ModelAndView sellerLogout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String, Object> model){
        //1. 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //2. 删除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));

            //3. 删除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        model.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
        model.put("redirect_uri", "/shop/index.html");
        return new ModelAndView("components/succeed", model);
    }
}

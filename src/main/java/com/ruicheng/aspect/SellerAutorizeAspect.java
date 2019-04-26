package com.ruicheng.aspect;

import com.ruicheng.constant.CookieConstant;
import com.ruicheng.constant.RedisConstant;
import com.ruicheng.exceptions.SellerAuthorizeException;
import com.ruicheng.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ruicheng
 * on 2019/4/23.
 */
@Aspect
@Component
@Slf4j
public class SellerAutorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.ruicheng.controller.Seller*.*(..))" +
            "&& !execution(public * com.ruicheng.controller.SellerUserController.seller*(..))")
    public void verify(){

    }

    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();

            //查询Cookie
            Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
            if (cookie == null){
                log.warn("[登录校验] 找不到cookie");
                throw new SellerAuthorizeException();
            }
            //查询redis
            String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            if (StringUtils.isEmpty(tokenValue)){
                log.warn("[登录校验] Redis中没有相应cookie");
                throw new SellerAuthorizeException();
            }
        }
    }


}

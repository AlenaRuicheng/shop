package com.ruicheng.handler;

import com.ruicheng.config.ProjectUrlConfig;
import com.ruicheng.exceptions.SellException;
import com.ruicheng.exceptions.SellerAuthorizeException;
import com.ruicheng.util.ResultVOUtil;
import com.ruicheng.viewobject.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Ruicheng
 * on 2019/4/23.
 */
@ControllerAdvice
public class SellerExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handleSellerAuthorizeException(){
        StringBuilder stringBuilder = new StringBuilder("redirect:");
        stringBuilder.append(projectUrlConfig.getShop())
                .append("/shop/index.html");
        return new ModelAndView(stringBuilder.toString());
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handleSellException(SellException se){
        return ResultVOUtil.onFailure(se.getCode(), se.getMessage());
    }
}

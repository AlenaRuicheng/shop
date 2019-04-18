package com.ruicheng.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Ruicheng
 * on 2019/2/25.
 */
@Data
public class OrderForm {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名是必填项")
    private String buyerName;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号是必填项")
    private String buyerPhone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址是必填项")
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid是必填项")
    private String buyerOpenid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}

package com.ruicheng.enums;

import lombok.Getter;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
@Getter
public enum ResultEnum {

    SUCCEED(0, "成功"),

    PARAMETER_ERROR(-1, "参数不正确"),

    NO_SUCH_PRODUCT(-2, "商品不存在"),

    PRODUCT_STOCK_ERROR(-3, "商品库存不正确"),

    ORDER_IS_NOT_PRESENT(-4, "订单不存在"),

    ORDER_DETAIL_IS_NOT_PRESENT(-5, "订单详情不存在"),

    ORDER_STATUS_ERROR(-6, "订单状态不正确"),

    ORDER_UPDATE_FAILED(-7, "订单更新失败"),

    EMPTY_ORDER_DETAIL(-8, "订单详情为空"),

    ORDER_PAY_STATUS_ERROR(-9, "订单支付状态不正确"),

    EMPTY_CART(-10, "购物车为空"),

    ORDER_OWNER_ERROR(-11, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(-12, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(-13, "微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(-14, "订单取消成功"),

    ORDER_FINISH_SUCCESS(-15, "订单完结成功"),

    PRODUCT_STATUS_ERROR(-16, "商品状态不正确"),

    LOGIN_FAIL(-17, "登录失败, 登录信息不正确"),

    LOGOUT_SUCCESS(-18, "登出成功"),

    NO_SUCH_CATEGORY(-19, "没有这个类目信息"),

    ENCODING_ERROR(-20, "编码错误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

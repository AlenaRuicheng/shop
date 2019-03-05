package com.ruicheng.enums;

import lombok.Getter;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCEED(1, "支付成功"),

    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

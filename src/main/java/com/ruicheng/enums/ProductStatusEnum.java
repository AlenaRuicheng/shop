package com.ruicheng.enums;

import lombok.Getter;

/**
 * Created by Ruicheng
 * on 2019/2/17.
 */
@Getter
public enum ProductStatusEnum implements CodeEnum {
    AVAILABLE(0, "在架"),
    OUT_OF_STOCK(1, "缺货")
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}

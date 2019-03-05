package com.ruicheng.exceptions;


import com.ruicheng.enums.ResultEnum;

/**
 * Created by Ruicheng
 * on 2019/2/18.
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

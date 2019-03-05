package com.ruicheng.viewobject;

import lombok.Data;

/**
 * http请求返回的最外层对象<br/>
 * Created by Ruicheng
 * on 2019/2/16.
 */
@Data
public class ResultVO<T> {

    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体内容. */
    private T data;
}

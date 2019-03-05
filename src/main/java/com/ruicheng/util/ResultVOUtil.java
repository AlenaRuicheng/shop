package com.ruicheng.util;


import com.ruicheng.viewobject.ResultVO;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
public class ResultVOUtil {

    public static ResultVO onSuccess(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO onSuccess() {
        return onSuccess(null);
    }

    public static ResultVO onFailure(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}

package com.ruicheng.util;


import com.ruicheng.enums.CodeEnum;

/**
 * Created by Ruicheng
 * on 2019/2/16.
 */
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}

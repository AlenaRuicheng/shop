package com.ruicheng.util;

/**
 * Created by Ruicheng
 * on 2019/4/18.
 */
public class VerificationUtil {
    private static final Double PRECISION = 0.01;

    public static Boolean equals(Double d1, Double d2){
        return  d1 - d2 < PRECISION;
    }
}

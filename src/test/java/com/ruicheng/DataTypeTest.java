package com.ruicheng;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Ruicheng
 * on 2019/2/7.
 */
public class DataTypeTest {
    @Test
    public void testBigDecimal(){
        System.out.println(0.2 + 0.1);

        BigDecimal bigDecimal = new BigDecimal(2);
        BigDecimal bDouble = new BigDecimal(2.3);
        BigDecimal bString = new BigDecimal("2.3");
        System.out.println("bigDecimal=" + bigDecimal);
        System.out.println("bDouble=" + bDouble);
        System.out.println("bString=" + bString);

        bDouble = BigDecimal.valueOf(2.3);
        System.out.println("new bDouble=" + bDouble);

        System.out.println("----------------------------------------");
        System.out.println(new BigDecimal(3.2).multiply(new BigDecimal(7)));
        System.out.println(BigDecimal.valueOf(3.2*7));
    }
}

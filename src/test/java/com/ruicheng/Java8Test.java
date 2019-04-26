package com.ruicheng;

import com.ruicheng.enums.PayStatusEnum;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ruicheng
 * on 2019/2/8.
 */
public class Java8Test {
    @Test
    public void testStream(){
        List<Integer> integerList = Arrays.asList(1,3,1,2,3,1,5);
        System.out.println(integerList);
        System.out.println(integerList.stream().distinct().collect(Collectors.toList()));
    }

    @Test
    public void testLambda(){
    }

    @Test
    public void test1(){
        for (PayStatusEnum pe : PayStatusEnum.values()) {
            System.out.println(PayStatusEnum.valueOf("WAIT"));
        }
    }

}

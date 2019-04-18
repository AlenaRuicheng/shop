package com.ruicheng;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
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

}

package com.ruicheng.util;

import java.util.Random;

/**
 * Created by Ruicheng
 * on 2019/2/18.
 */
public class KeyGenerator {

    /**
     * 生成唯一的主键(加synchronized是防止多线程产生相同的主键)
     * 格式: 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}

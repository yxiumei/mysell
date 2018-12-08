package com.dtdream.mysell.utils;

import java.util.Random;

/**
 * @author yxiumei
 */
public class KeyUtil {

    public static synchronized String  getUniqueKey(){
        Random random = new Random();
        Integer num = random.nextInt(9000) + 1000;
        String key = System.currentTimeMillis() + String.valueOf(num);
        return key.substring(9,key.length()-1);
    }
}

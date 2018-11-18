package com.dtdream.mysell.utils;

/**
 * @Author yxiumei
 * @Data 2018/11/18 19:37
 */
public class MethUtils {

    private static final double NUM = 0.1;
    /**
     * 比较两个double 是否相等
     * @param b1
     * @param b2
     * @return
     */
    public static boolean compareTo(double b1,double b2){
        return b1 - b2 < NUM ? true:false;
    }
}

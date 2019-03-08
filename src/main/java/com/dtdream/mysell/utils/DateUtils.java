package com.dtdream.mysell.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author yxiumei
 * @Data 2019/3/8 18:36
 */
public class DateUtils {

    //判断选择的日期是否是本月
    public static boolean isThisMonth(long time) {
        return isThisTime(time, "yyyy-MM");
    }

    public static boolean isThisTime(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //参数时间
        String param = sdf.format(date);
        //当前时间
        String now = sdf.format(new Date());
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

}

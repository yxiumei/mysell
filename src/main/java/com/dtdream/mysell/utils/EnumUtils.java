package com.dtdream.mysell.utils;

import com.dtdream.mysell.enums.CodeEnum;

/**
 * @Author yxiumei
 * @Data 2018/11/24 21:55
 */
public class EnumUtils {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumsClass){
        for (T each: enumsClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}

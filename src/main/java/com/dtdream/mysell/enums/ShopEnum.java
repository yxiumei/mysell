package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * @Author yxiumei
 * @Data 2018/12/10 22:45
 */
@Getter
public enum  ShopEnum {

    CANCEL(0 ,"注销"),
    NORMAL(1, "正常")
        ;

    private Integer code;
    private String msg;

    ShopEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

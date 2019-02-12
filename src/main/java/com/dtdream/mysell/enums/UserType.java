package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * @Author yxiumei
 * @Data 2019/2/12 21:00
 */
@Getter
public enum UserType {

    ORDINARY_USERS(0 ,"普通用户"),
    ADMINISTRATORS(1 ,"管理员")
    ;

    private Integer code;
    private String msg;

    UserType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

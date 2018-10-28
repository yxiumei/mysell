package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * @author 杨秀眉
 */
@Getter
public enum  PayStatusEum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),

    ;

    private Integer code;
    private String msg;

    PayStatusEum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

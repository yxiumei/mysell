package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * 订单枚举
 * @author yxiumei
 */
@Getter
public enum OrderStatusEnum implements CodeEnum{

    NEW(0, "新单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

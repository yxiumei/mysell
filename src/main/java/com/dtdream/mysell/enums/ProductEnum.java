package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * 商品状态
 * @author 杨秀眉
 */
@Getter
public enum ProductEnum {

    UP_FRAME(0, "下架"),
    DOWN_FRAME(1, "上架"),
    DELETE(-1, "删除")
    ;

    private Integer code;
    private String msg;

    ProductEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

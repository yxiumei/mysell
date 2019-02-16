package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * 评论类型
 * @Author yxiumei
 * @Data 2019/2/16 14:38
 */
@Getter
public enum  CommentTypeEnum {

    /**
     * 好评
     */
    GOOD_COMMENT(0, "好评"),
    /**
     * 差评
     */
    NEGATIVE_COMMENT(1, "差评"),
    ;

    private Integer code;
    private String msg;

    CommentTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

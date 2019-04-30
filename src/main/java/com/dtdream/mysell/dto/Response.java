package com.dtdream.mysell.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * http请求返回最外成对象
 * @author yxiumei
 * @param <T>
 */
@Data
public class Response<T> implements Serializable {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;

    /**
     *
     * @param obj
     * @return
     */
    private boolean isSuccess;

    public Response(Integer code, String msg, T data, boolean isSuccess) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public static <T> Response<T> ok(Object obj){
        return new Response(200, "成功", obj, Boolean.TRUE);
    }

    public static <T> Response<T> fail(String str){
        return new Response(500, str, (Object)null ,  Boolean.FALSE);
    }
}

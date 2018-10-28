package com.dtdream.mysell.dto;

import lombok.Data;

/**
 * http请求返回最外成对象
 * @author 杨秀眉
 * @param <T>
 */
@Data
public class Response<T> {

    /** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 具体内容 */
    private T data;

    public static Response ok(Object obj){
        Response response = new Response();
        response.setData(obj);
        response.setCode(200);
        response.setMsg("成功");
        return response;
    }

    public static Response fail(String str){
        Response response = new Response();
        response.setMsg(str);
        response.setCode(500);
        return response;
    }
}

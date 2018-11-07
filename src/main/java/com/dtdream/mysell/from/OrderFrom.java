package com.dtdream.mysell.from;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单表单验证
 * @Author yxiumei
 * @Data 2018/10/30 23:29
 */
@Data
public class OrderFrom {

    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车信息
     */
    @NotEmpty(message = "购物车信息不能为空")
    private String items;

}

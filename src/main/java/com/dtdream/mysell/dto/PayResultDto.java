package com.dtdream.mysell.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付返回结果
 * @Author yxiumei
 * @Data 2018/12/15 20:54
 */
@Data
public class PayResultDto {

    /**
     * 订单号
     */
    private String orderId;
    /**
     * 支付时间
     */
    private String payDate;
    /**
     * 订单金额
     */
    private  BigDecimal amount;
    /**
     * 支付方式
     */
    private String payWey;


}

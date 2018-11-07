package com.dtdream.mysell.dto;

import com.dtdream.mysell.model.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yxiumei
 */
@Data
public class OrderDto {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    /**
     * 买家微信openid
     */
    private String buyerOpenid;
    private BigDecimal oderAmount;
    /**
     * 订单状态 默认0:新下单
     */
    private Integer orderStatus;
    /** 支付状态 0: 默认等待支付*/
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetails;
}

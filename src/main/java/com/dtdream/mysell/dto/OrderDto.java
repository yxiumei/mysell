package com.dtdream.mysell.dto;

import com.dtdream.mysell.enums.OrderStatusEnum;
import com.dtdream.mysell.enums.PayStatusEum;
import com.dtdream.mysell.model.OrderDetail;
import com.dtdream.mysell.utils.Date2LongSerializer;
import com.dtdream.mysell.utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    /**
     *  买家用户头像url
     */
    private String haedPortrait;
    private BigDecimal orderAmount;
    /**
     * 订单状态 默认0:新下单
     */
    @JsonIgnore
    private Integer orderStatus;
    private String orderStatusStr;

    /** 支付状态 0: 默认等待支付*/
    @JsonIgnore
    private Integer payStatus;
    private String payStatusStr;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    private List<OrderDetail> orderDetails;

    public String getOrderStatusStr() {
        return getOrderStatusEnum().getMsg();
    }

    public String getPayStatusStr() {
        return getPayStatusEnum().getMsg();
    }

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtils.getByCode(orderStatus,OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEum getPayStatusEnum(){
        return EnumUtils.getByCode(payStatus,PayStatusEum.class);
    }
}

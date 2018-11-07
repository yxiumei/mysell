package com.dtdream.mysell.model;

import com.dtdream.mysell.enums.OrderStatusEnum;
import com.dtdream.mysell.enums.PayStatusEum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单主表
 * @author yxiumei
 */
@Data
public class OrderMaster {

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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态 0: 默认等待支付*/
    private Integer payStatus = PayStatusEum.WAIT.getCode();
    private Date createTime;
    private Date updateTime;
}

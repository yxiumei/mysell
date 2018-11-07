package com.dtdream.mysell.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情
 * @author yxiumei
 */
@Data
public class OrderDetail {

    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    /** 单价 */
    private BigDecimal productPrice;
    /** 库存 */
    private Integer productQuantity;
    private Date createTime;
    private Date updateTime;
}

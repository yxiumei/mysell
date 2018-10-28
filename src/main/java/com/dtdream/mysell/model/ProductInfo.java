package com.dtdream.mysell.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品详情表
 * @author 杨秀眉
 */
@Data
public class ProductInfo {

    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productStock;
    private String productDescription;
    private String productImg;
    private Integer categoryType;
    /** 商品状态 0:下架  1：上架   -1：删除*/
    private Integer status;
    private Date createTime;
    private Date updateTime;

}

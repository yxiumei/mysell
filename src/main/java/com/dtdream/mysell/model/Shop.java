package com.dtdream.mysell.model;

import lombok.Data;

import java.util.Date;

/**
 * 店铺信息
 * @author yxiumei
 */
@Data
public class Shop {
    private Integer id;

    private String shopName;

    private String description;

    /**
     * 配送时间
     */
    private Long deliveryTime;

    /**
     * 最低价
     */
    private Double minPrice;

    /**
     * 起送价格
     */
    private Double deliveryPrice;

    /**
     * 公告
     */
    private String bulletin;

    /**
     * 店铺主图
     */
    private String avatar;

    /**
     * 商家图片：
     */
    private String pics;

    /**
     * infos
     */
    private String infos;

    /**
     * 0:注销 1：正常
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}
package com.dtdream.mysell.model;

import lombok.Data;

import java.util.Date;

/**
 * 店铺评分信息
 * @author yxiumei
 */
@Data
public class ShopScore {
    private String id;

    /**
     * 店铺id
     */
    private String  shopId;

    /**
     * 商家综合评分
     */
    private Double score;

    /**
     * 服务态度评分
     */
    private Double serviceScore;

    /**
     * 商品评分
     */
    private Double foodScore;

    /**
     * 和其他商家对比评分
     */
    private Double rankRate;

    /**
     * 评论总数
     */
    private Integer ratingCount;

    /**
     * 好评
     */
    private Integer highOpinion;

    /**
     * 差评
     */
    private Integer differOpinion;

    /**
     * 月销售量
     */
    private Integer sellCount;

    private Date createTime;

    private Date updateTime;

}
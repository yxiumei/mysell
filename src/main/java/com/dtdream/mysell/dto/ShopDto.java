package com.dtdream.mysell.dto;

import com.dtdream.mysell.model.MarketingCampaign;
import lombok.Data;

import java.util.List;

/**
 *  店铺信息
 * @Author yxiumei
 * @Data 2018/12/10 22:10
 */
@Data
public class ShopDto {

    private String id;
    private String name;
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

    /** 优惠 */
    private List<MarketingCampaign> supports;

}

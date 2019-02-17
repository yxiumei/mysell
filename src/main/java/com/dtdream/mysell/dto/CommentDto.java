package com.dtdream.mysell.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author yxiumei
 * @Data 2019/2/16 18:10
 */
@Data
public class CommentDto implements Serializable{
    private static final long serialVersionUID = 8137798851843894251L;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 服务态度评分
     */
    private Integer serviceScore;

    /**
     *  商品评分
     */
    private Integer foodScore;

    /**
     * 是否推荐商品 0:推荐
     */
    private Integer recommend;

    /**
     * 评论内容
     */
    private String text;
}

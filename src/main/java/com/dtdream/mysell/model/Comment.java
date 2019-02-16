package com.dtdream.mysell.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * 评论信息
 * @author yxiumei
 */
@Data
public class Comment implements Serializable{
    private static final long serialVersionUID = 4631806270545558661L;
    private Integer id;

    /*
     * 订单id
     */
    private String orderId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 评论时间
     */
    private Date rateTime;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 评论类型  0：好评   1：差评
     */
    private Integer rateType;

    /**
     * 评论内容
     */
    private String text;

    /**
     * 评论者头像
     */
    private String avatar;

    /**
     *  推荐商品(不为空表示推荐本次商品)
     */
    private String recommend;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
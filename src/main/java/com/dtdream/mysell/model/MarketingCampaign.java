package com.dtdream.mysell.model;

import lombok.Data;

import java.util.Date;

/**
 * 营销活动
 * @author yxiumei
 */
@Data
public class MarketingCampaign {

    private Integer id;
    /**
     * 活动名
     */
    private String activityName;

    /**
     * 活动描述
     */
    private String activityDescription;

    /**
     * 活动类型
     */
    private Integer activityType;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 0:结束 1：正常
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

}
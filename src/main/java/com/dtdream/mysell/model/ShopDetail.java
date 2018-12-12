package com.dtdream.mysell.model;

import lombok.Data;

import java.util.Date;

@Data
public class ShopDetail {
    private Integer id;

    private String shopId;

    /**
     * 公告
     */
    private String bulletin;

    /** 图片*/
    private String pics;

    private String infos;

    private Integer status;

    private Date createTime;

    private Date updateTime;

}
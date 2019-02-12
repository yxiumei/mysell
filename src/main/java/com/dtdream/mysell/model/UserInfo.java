package com.dtdream.mysell.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * @author yxiumei
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = -6806540941494017259L;

    private Integer id;

    private String username;

    private String password;

    private String openid;

    /**
     *  用户类型 0：普通用户，1：管理员
     */
    private Integer userType;

    /**
     * 用户头像
     */
    private String userImg;

    private Date createTime;

    private Date updateTime;
}
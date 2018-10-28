package com.dtdream.mysell.model;

import lombok.Data;

import java.util.Date;

/**
 * 类目
 * @author 杨秀眉
 */
@Data
public class ProductCategory {

    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}

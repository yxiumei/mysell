package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.Shop;

/**
 * 店铺信息
 * @author yxiumei
 */
public interface ShopMapper {

    int insert(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int update(Shop record);

}
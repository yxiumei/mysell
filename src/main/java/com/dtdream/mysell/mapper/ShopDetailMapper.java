package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.ShopDetail;

public interface ShopDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopDetail record);

    ShopDetail selectByPrimaryKey(Integer id);

    int update(ShopDetail record);

}
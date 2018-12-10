package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺信息
 * @author yxiumei
 */
public interface ShopMapper {

    int insert(Shop record);

    Shop selectByPrimaryKey(String id);

    int update(Shop record);

    /**
     * 查询所有店铺
     * @return
     */
    List<Shop> findAll(@Param("shopName") String shopName);

    /**
     * 查询最晚改变的店铺
     * @return
     */
    Shop findLastUpdateShop();

}
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

    /**
     * 通过通过店铺id，查询店铺信息
     * @param shopId 店铺id
     * @return 店铺信息
     */
    Shop selectByPrimaryKey(@Param("shopId") String shopId);

    int updateByPrimaryKeySelective(Shop record);

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
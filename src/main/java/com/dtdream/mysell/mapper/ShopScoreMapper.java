package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.ShopScore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺评分
 * @author yxiumei
 */
public interface ShopScoreMapper {

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(ShopScore record);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    ShopScore selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int update(ShopScore record);

    /**
     * 通过店铺id查询评分
     * @param shopId
     * @return
     */
    ShopScore findShopScoreByShopId(@Param("shopId") String shopId);

    /**
     * 查询所有店铺评分
     * @return
     */
    List<ShopScore> findAll();
}
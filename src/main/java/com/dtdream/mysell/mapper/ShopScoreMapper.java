package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.ShopScore;

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
}
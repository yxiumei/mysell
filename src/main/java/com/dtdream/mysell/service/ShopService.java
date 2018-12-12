package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.dto.ShopImagesDto;
import com.dtdream.mysell.model.Shop;
import com.github.pagehelper.PageInfo;

/**
 * 店铺信息
 * @Author yxiumei
 * @Data 2018/12/10 22:22
 */
public interface ShopService {

    /**
     * 保存店铺
     * @param shop
     * @return
     */
    Response<Boolean> save(ShopImagesDto shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    Response<Boolean> update(ShopImagesDto shop);

    /**
     * 注销店铺
     */
    Response<Boolean> cancel(String shopId);

    /**
     * 通过id查询
     * @param shopId
     * @return
     */
    Response<Shop> findOne(String shopId);

    /**
     * 查询店铺列表
     * @param page
     * @param pageSize
     * @param key
     * @return
     */
    Response<PageInfo<Shop>> findAll(Integer page, Integer pageSize, String key);
}

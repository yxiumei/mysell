package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.Shop;

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
    Response<Boolean> save(Shop shop);

    /**
     * 更新店铺信息
     * @param shop
     * @return
     */
    Response<Boolean> update(Shop shop);

    /**
     * 注销店铺
     */
    Response<Boolean> cancel(String shopId);
}

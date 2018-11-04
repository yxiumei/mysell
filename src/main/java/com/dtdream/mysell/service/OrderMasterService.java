package com.dtdream.mysell.service;

import com.dtdream.mysell.model.OrderMaster;
import com.github.pagehelper.PageInfo;

public interface OrderMasterService {

    /**
     * 查询商品列表通过openId
     * @param pageNo
     * @param pageSize
     * @param openId
     * @return
     */
    PageInfo<OrderMaster> findOrderMasterByOpenId(Integer pageNo, Integer pageSize, String openId);
}

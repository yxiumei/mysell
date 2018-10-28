package com.dtdream.mysell.service;

import com.dtdream.mysell.model.OrderMaster;
import com.github.pagehelper.PageInfo;

public interface OrderMasterService {

    PageInfo<OrderMaster> findOrderMasterByOpenId(Integer pageNo, Integer pageSize, String openId);
}

package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.github.pagehelper.PageInfo;

public interface OrderMasterService {

    /**
     * 查询订单列表通过openId
     * @param pageNo
     * @param pageSize
     * @param openId
     * @return
     */
    PageInfo<OrderDto> findOrderMasterByOpenId(Integer pageNo, Integer pageSize, String openId);

    /**
     * 查询所有订单列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    Response<PageInfo<OrderDto>> findAll(Integer pageNo, Integer pageSize);
}

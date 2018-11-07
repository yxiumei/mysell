package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;

/**
 * 买家
 * @Author yxiumei
 * @Data 2018/11/4 21:01
 */
public interface BuyerService {

    /**
     * 查询一个订单
     * @param orderId
     * @param openid
     * @return
     */
    Response<OrderDto> findOrderOne(String orderId,String openid);

    /**
     * 取消一个订单
     * @param orderId 订单id
     * @param openid
     * @return
     */
    Response<OrderDto> cancelOrderOne(String orderId, String openid);
}

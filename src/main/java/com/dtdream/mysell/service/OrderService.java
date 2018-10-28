package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.github.pagehelper.PageInfo;

/**
 * @author 杨秀眉
 */
public interface OrderService {

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    Response<OrderDto> create(OrderDto orderDto);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    Response<OrderDto> findOne(String orderId);

    /**
     * 查询订单列表
     * @param pageNo
     * @param pageSize
     * @param buyerOpenId
     * @return
     */
    Response<PageInfo<OrderDto>> findOrderList(Integer pageNo, Integer pageSize, String buyerOpenId);

    /**
     *  取消订单
     * @param orderDto
     * @return
     */
    Response<OrderDto> cancel(OrderDto orderDto);

    /**
     * 完结订单
     * @param orderDto
     * @return
     */
    Response<OrderDto> fainish(OrderDto orderDto);

    /**
     *  支付订单
     * @param orderDto
     * @return
     */
    Response<OrderDto> paidOrder(OrderDto orderDto);

}

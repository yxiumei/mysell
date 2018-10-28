package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.OrderDetail;

import java.util.List;

/**
 * @author 杨秀眉
 */
public interface OrderDetailMapper {
    int deleteByPrimaryKey(String detailId);

    int insert(OrderDetail record);

    int insertSelective(OrderDetail record);

    OrderDetail selectByPrimaryKey(String detailId);

    int updateByPrimaryKeySelective(OrderDetail record);

    int updateByPrimaryKey(OrderDetail record);

    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrder(String orderId);

    /**
     * 通过商品id查询
     */
    OrderDetail findByProductId(String productId);

    /**
     * 批量插入
     * @param list
     * @return
     */
    Long batchInser(List<OrderDetail> list);
}
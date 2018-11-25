package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yxiumei
 */
public interface OrderMasterMapper {

    /**
     * 通过id删除
     * @param orderId
     * @return
     */
    int deleteByPrimaryKey(String orderId);

    /**
     * 插入订单数据
     * @param record
     * @return
     */
    int insert(OrderMaster record);

    /**
     * 查询订单信息
     * @param orderId
     * @return
     */
    OrderMaster selectByPrimaryKey(String orderId);

    /**
     * 更改订单信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderMaster record);

    /**
     * 通过oponId 查询主订单
     * @param openId
     * @return
     */
    List<OrderMaster> findByBuyOpenId(@Param("oponId") String openId);

    /**
     * 查询所有订单
     * @return
     */
    List<OrderMaster> findAll();
}
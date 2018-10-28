package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.OrderMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 杨秀眉
 */
public interface OrderMasterMapper {

    int deleteByPrimaryKey(String orderId);

    int insert(OrderMaster record);

    int insertSelective(OrderMaster record);

    OrderMaster selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderMaster record);

    int updateByPrimaryKey(OrderMaster record);

    /**
     * 通过oponId 查询主订单
     * @param openId
     * @return
     */
    List<OrderMaster> findByBuyOpenId(@Param("oponId") String openId);
}
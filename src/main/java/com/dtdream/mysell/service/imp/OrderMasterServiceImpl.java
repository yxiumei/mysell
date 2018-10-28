package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.mapper.OrderMasterMapper;
import com.dtdream.mysell.model.OrderMaster;
import com.dtdream.mysell.service.OrderMasterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 杨秀眉
 */
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    public PageInfo<OrderMaster> findOrderMasterByOpenId(
            Integer pageNo, Integer pageSize, String openId) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderMaster> byBuyOpenId = orderMasterMapper.findByBuyOpenId(openId);
        PageInfo<OrderMaster> pageInfo = new PageInfo<>(byBuyOpenId);
        return pageInfo;
    }
}

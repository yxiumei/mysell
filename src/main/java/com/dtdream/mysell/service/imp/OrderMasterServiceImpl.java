package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.mapper.OrderMasterMapper;
import com.dtdream.mysell.model.OrderMaster;
import com.dtdream.mysell.service.OrderMasterService;
import com.dtdream.mysell.utils.OrderMaster2OrderDtoConverter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yxiumei
 */
@Slf4j
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Override
    public PageInfo<OrderDto> findOrderMasterByOpenId(
            Integer pageNo, Integer pageSize, String openId) {
        PageHelper.startPage(pageNo, pageSize);
        List<OrderMaster> byBuyOpenId = orderMasterMapper.findByBuyOpenId(openId);
        List<OrderDto> dtoList = OrderMaster2OrderDtoConverter.convert(byBuyOpenId);
        PageInfo<OrderDto> pageInfo = new PageInfo<>(dtoList);
        return pageInfo;
    }

    @Override
    public Response<PageInfo<OrderDto>> findAll(Integer pageNo, Integer pageSize) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<OrderMaster> byBuyOpenId = orderMasterMapper.findAll();
        List<OrderDto> dtoList = OrderMaster2OrderDtoConverter.convert(byBuyOpenId);
        PageInfo<OrderDto> pageInfo = new PageInfo<>(page);
        pageInfo.setList(dtoList);
        return Response.ok(pageInfo);
    }
}

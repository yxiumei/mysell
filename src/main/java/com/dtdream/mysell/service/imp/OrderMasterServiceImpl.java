package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.dto.SalesAmountDto;
import com.dtdream.mysell.mapper.OrderMasterMapper;
import com.dtdream.mysell.model.OrderMaster;
import com.dtdream.mysell.service.OrderMasterService;
import com.dtdream.mysell.utils.DateUtils;
import com.dtdream.mysell.utils.OrderMaster2OrderDtoConverter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yxiumei
 */
@Slf4j
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired(required = false)
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

    @Override
    public Response<SalesAmountDto> getSalesAount() {
        SalesAmountDto salesAmountDto = new SalesAmountDto();
        List<OrderMaster> orderMasters = orderMasterMapper.findAll();
        BigDecimal total = new BigDecimal(0);
        BigDecimal totalMonth = new BigDecimal(0);
        if (!CollectionUtils.isEmpty(orderMasters)) {
            for (OrderMaster order : orderMasters) {
                BigDecimal oderAmount = order.getOderAmount();
                Date createTime = order.getCreateTime();
                total = total.add(oderAmount);
                // 是否时当月
                if (DateUtils.isThisMonth(createTime.getTime())) {
                    totalMonth = totalMonth.add(oderAmount);
                }
            }
        }
        salesAmountDto.setThisMonth(totalMonth.doubleValue());
        salesAmountDto.setTotalAmout(total.doubleValue());

        return Response.ok(salesAmountDto);
    }
}

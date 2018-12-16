package com.dtdream.mysell.manage;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.mapper.OrderDetailMapper;
import com.dtdream.mysell.mapper.OrderMasterMapper;
import com.dtdream.mysell.model.OrderDetail;
import com.dtdream.mysell.model.OrderMaster;
import com.dtdream.mysell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author yxiumei
 */
@Component
public class OrderManage {

    @Autowired(required = false)
    private OrderDetailMapper orderDetailMapper;
    @Autowired(required = false)
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private ProductService productService;
    /**
     * 新增订单
     * @param orderTetails 订单详情
     * @param orderMaster  主订单
     * @param cartDtoList 购物车
     */
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(List<OrderDetail> orderTetails, OrderMaster orderMaster,
                            List<CartDto> cartDtoList) throws Exception {
        Long aLong = orderDetailMapper.batchInsert(orderTetails);
        if (0 == aLong){
            throw new Exception();
        }
        Date date = new Date();
        orderMaster.setCreateTime(date);
        orderMaster.setUpdateTime(date);
        Integer i = orderMasterMapper.insert(orderMaster);
        if(0 == i){
            throw new Exception();
        }
        Response<Boolean> response = productService.decreaseStock(cartDtoList);
        if (!response.getData()){
            throw new Exception();
        }
    }

    /**
     * 取消订单
     * @param orderMaster
     * @param cartDtos
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancel(OrderMaster orderMaster, List<CartDto> cartDtos) throws Exception {
        Integer result = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        if (0 == result){
            throw  new Exception();
        }
        Response<Boolean> booleanResponse = productService.increaseStock(cartDtos);
        if (!booleanResponse.getData()){
            throw new Exception();
        }
    }
}

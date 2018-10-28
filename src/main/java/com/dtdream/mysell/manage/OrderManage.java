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

import java.util.List;

/**
 * 订单
 * @author 杨秀眉
 */
@Component
public class OrderManage {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
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
        Long aLong = orderDetailMapper.batchInser(orderTetails);
        if (0 == aLong){
            throw new Exception();
        }
        Integer i = orderMasterMapper.insertSelective(orderMaster);
        if(0 == i){
            throw new Exception();
        }
        Response<Boolean> response = productService.decreaseStock(cartDtoList);
        if (!response.getData()){
            throw new Exception();
        }
    }
}

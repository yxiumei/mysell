package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.service.BuyerService;
import com.dtdream.mysell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 买家端
 * @Author 杨秀眉
 * @Data 2018/11/4 21:04
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;
    @Override
    public Response<OrderDto> findOrderOne(String orderId, String openid) {
        Response<OrderDto> one = orderService.findOne(orderId);
        OrderDto data = one.getData();
        if (ObjectUtils.isEmpty(data)){
            log.error("OP[]BuyerServiceImpl[]findOrderOne[]get order info fail");
            return Response.fail(ErrorMessage.GET_ORDER_DETAIL_FAIL.toString());
        }
        // 判断openid是否相同
        if (!openid.equals(data.getBuyerOpenid())){
            return Response.fail(ErrorMessage.PRODUCT_NOT_EXIST.toString());
        }
        return Response.ok(data);
    }

    @Override
    public Response<OrderDto> cancelOrderOne(String orderId, String openid) {
        Response<OrderDto> orderOne = findOrderOne(orderId, openid);
        OrderDto orderDto = orderOne.getData();
        if (ObjectUtils.isEmpty(orderDto)){
            log.error("OP[]BuyerServiceImpl[]cancelOrderOne[]orderDto:{}",orderDto);
            return Response.fail(ErrorMessage.GET_ORDER_DETAIL_FAIL.toString());
        }
        Response<OrderDto> cancel = orderService.cancel(orderDto);
        return cancel;
    }
}

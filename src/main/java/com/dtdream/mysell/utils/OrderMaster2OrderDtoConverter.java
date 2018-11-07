package com.dtdream.mysell.utils;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yxiumei
 */
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasters){
        return  orderMasters.stream().map(e ->
            convert(e)).collect(Collectors.toList());
    }
}

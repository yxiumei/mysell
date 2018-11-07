package com.dtdream.mysell.utils;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.from.OrderFrom;
import com.dtdream.mysell.model.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author yxiumei
 * @Data 2018/10/30 23:41
 */
@Slf4j
public class OrderFrom2OrderDtoConverter {

    public static OrderDto convert(OrderFrom orderFrom) throws Exception {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderFrom.getName());
        orderDto.setBuyerPhone(orderFrom.getPhone());
        orderDto.setBuyerAddress(orderFrom.getAddress());
        orderDto.setBuyerOpenid(orderFrom.getOpenid());
        List<OrderDetail> list;
        try{
            list = gson.fromJson(orderFrom.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        }catch (Exception e){
           log.error("OP[]OrderFrom2OrderDtoConverter[]convert[]convert error:string = {}", orderFrom.getItems());
            throw  new Exception();
        }
        orderDto.setOrderDetails(list);
        return orderDto;
    }
}

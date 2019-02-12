package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.model.UserInfo;
import com.dtdream.mysell.service.BuyerService;
import com.dtdream.mysell.service.OrderService;
import com.dtdream.mysell.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 买家端
 * @Author yxiumei
 * @Data 2018/11/4 21:04
 */
@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserInfoService userInfoService;
    @Override
    public Response<OrderDto> findOrderOne(String orderId, String openid) {
        Response<OrderDto> one = orderService.findOne(orderId);
        OrderDto data = one.getData();
        if (ObjectUtils.isEmpty(data)){
            log.info("OP[]BuyerServiceImpl[]findOrderOne[]get order info fail");
            return Response.ok(null);
        }
        // 判断openid是否相同
        if (!openid.equals(data.getBuyerOpenid())){
            return Response.fail(ErrorMessage.PRODUCT_NOT_EXIST.toString());
        }
        // 通过openid查询用户信息
        Response<UserInfo> userInfoByOpenId = userInfoService.findUserInfoByOpenId(openid);
        if (userInfoByOpenId.isSuccess() && null != userInfoByOpenId.getData()) {
            data.setHaedPortrait(userInfoByOpenId.getData().getUserImg());
        }
        return Response.ok(data);
    }

    @Override
    public Response<OrderDto> cancelOrderOne(String orderId, String openid) {
        Response<OrderDto> orderOne = findOrderOne(orderId, openid);
        OrderDto orderDto = orderOne.getData();
        if (ObjectUtils.isEmpty(orderDto)){
            log.info("OP[]BuyerServiceImpl[]cancelOrderOne[]orderDto:{}",orderDto);
            return Response.ok(ErrorMessage.GET_ORDER_DETAIL_FAIL.toString());
        }
        Response<OrderDto> cancel = orderService.cancel(orderDto);
        return cancel;
    }
}

package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.PayResultDto;
import com.dtdream.mysell.dto.Response;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;

/**
 * @Author yxiumei
 * @Data 2018/11/18 15:02
 */
public interface PayService {

    /**
     * 支付发起
     * @param data
     * @return
     */
    Response<PayResponse> create(OrderDto data);

    /**
     * 异步通知
     * @param notifyData
     * @return
     */
    Response<PayResponse> notify(String notifyData);

    /**
     * 退款
     * @param orderDto
     * @return
     */
    Response<RefundResponse> refund(OrderDto orderDto);

    /**
     * 仿 退款
     * @param orderDto
     * @return
     */
    Response<Boolean> imitateRefund(OrderDto orderDto);

    /**
     * 仿微信支付
     * @param orderId
     * @return
     */
    Response<PayResultDto> pay(String orderId);
}

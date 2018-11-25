package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.service.OrderService;
import com.dtdream.mysell.service.PayService;
import com.dtdream.mysell.utils.MethUtils;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付
 * @Author yxiumei
 * @Data 2018/11/18 15:32
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信支付";

    @Autowired
    private OrderService orderService;
    @Autowired
    private BestPayServiceImpl bestPayService;

    @Override
    public Response<PayResponse> create(OrderDto data) {
        try{
            PayRequest payRequest = new PayRequest();
            payRequest.setOpenid(data.getBuyerOpenid());
            payRequest.setOrderAmount(data.getOderAmount().doubleValue());
            payRequest.setOrderId(data.getOrderId());
            payRequest.setOrderName(ORDER_NAME);
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
            log.info("【微信支付参数】。request:{}", payRequest);
            PayResponse pay = bestPayService.pay(payRequest);
            log.info("【微信支付发起】，response:{}",pay);
            return Response.ok(pay);
        }catch (Exception e){
            log.error("OP[]PayServiceImpl[]create[]weixin pay crate fail");
            return Response.fail(ErrorMessage.ORDER_PAY_FAIL.toString());
        }
    }

    @Override
    public Response<PayResponse> notify(String notifyData) {
        try{
            PayResponse payResponse = bestPayService.asyncNotify(notifyData);
            log.info("OP[]PayServiceImpl[]notify[]weixin notify:{}",payResponse);
            Response<OrderDto> one = orderService.findOne(payResponse.getOrderId());
            if (!one.isSuccess()) {
                log.error("OP[]PayServiceImpl[]notify[]OrderDto is:{}",payResponse);
                return Response.fail(ErrorMessage.GET_ORDER_DETAIL_FAIL.toString());
            }
            OrderDto orderDto = one.getData();
            // 判断金额是否一致
            if (!MethUtils.compareTo(payResponse.getOrderAmount(),orderDto.getOderAmount().doubleValue())){
                log.error("OP[]PayServiceImpl[]notify[]orderAmount:{},payAmount:{}",
                        orderDto.getOderAmount(),payResponse.getOrderAmount());
                return Response.fail(ErrorMessage.ORDER_AMOUNT_DIFFER.toString());
            }
            Response<OrderDto> orderDtoResponse = orderService.paidOrder(orderDto);
            if (!orderDtoResponse.isSuccess()){
                log.error("OP[]PayServiceImpl[]notify[]orderDtoResponse:{}",orderDtoResponse);
                return Response.fail(ErrorMessage.FINISH_ORDER_FAIL.toString());
            }
            return Response.ok(payResponse);
        }catch (Exception e){
            log.error("OP[]PayServiceImpl[]notify[]update order status fail");
            return Response.fail(ErrorMessage.AWEIXIN_NOTIFU_FAIL.toString());
        }
    }

    @Override
    public Response<RefundResponse> refund(OrderDto orderDto) {
        log.error("OP[]PayServiceImpl[]refund[]OrderDto:{}",orderDto);
        try{
            RefundRequest refundRequest = new RefundRequest();
            refundRequest.setOrderId(orderDto.getOrderId());
            refundRequest.setOrderAmount(orderDto.getOderAmount().doubleValue());
            refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
            RefundResponse refund = bestPayService.refund(refundRequest);
            return Response.ok(refund);
        }catch (Exception e){
            log.error("OP[]PayServiceImpl[]refund[]refund fail");
            return Response.fail(ErrorMessage.WEIXIN_REFUND_FAIL.toString());
        }
    }
}
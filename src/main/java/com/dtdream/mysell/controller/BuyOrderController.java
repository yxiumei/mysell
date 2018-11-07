package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.service.BuyerService;
import com.dtdream.mysell.service.OrderService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 买家端订单
 * @Author yxiumei
 * @Data 2018/10/30 23:26
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    @PostMapping("/create")
    public Response<Map<String, String>> create(@RequestBody OrderDto orderDto ){
        try {
            if (ObjectUtils.isEmpty(orderDto)) {
                log.error("OP[]BuyOrderController[]create[]param error:{}", orderDto);
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            if (CollectionUtils.isEmpty(orderDto.getOrderDetails())) {
                log.error("OP[]BuyOrderController[]create[]cart is null");
                return Response.fail(ErrorMessage.CART_IS_NULL.toString());
            }
            return orderService.create(orderDto);

        } catch (Exception e) {
            log.error("OP[]BuyOrderController[]create[]creat order fail error:{}",e.fillInStackTrace());
            return Response.fail(ErrorMessage.CREATE_ORDER_FAIL.toString());
        }

    }

    /**
     * 获得订单列表
     * @param openid
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Response<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        log.info("OP[]BuyOrderController[]list[]openid:{}", openid);
        try{
            if (StringUtils.isEmpty(openid)){
                log.error("OP[]BuyOrderController[]list[]param openid:{}", openid);
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            Response<PageInfo<OrderDto>> orderList = orderService.findOrderList(pageNo, pageSize, openid);
            return Response.ok(orderList.getData().getList());
        }catch (Exception e){
            log.error("OP[]BuyOrderController[]create[]get order list fail error:{}", e.fillInStackTrace());
            return Response.fail(ErrorMessage.GET_ORDER_LIST_FAIL.toString());
        }

    }

    @GetMapping("/detail")
    public Response<OrderDto> detail(@RequestParam String openid,
                                     @RequestParam String orderId){
        log.info("OP[]BuyOrderController[]detail[]openid:{},orderId:{}", openid,orderId);
        try{
            if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(orderId)){
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            Response<OrderDto> orderOne = buyerService.findOrderOne(orderId, openid);
            return orderOne;
        } catch (Exception e){
            log.error("OP[]BuyOrderController[]detail[]get order info fail");
            return Response.fail(ErrorMessage.GET_ORDER_DETAIL_FAIL.toString());
        }
    }


    @GetMapping("/cancel")
    public Response<Boolean> cancel(@RequestParam String openid,
                                    @RequestParam String orderId){
        log.info("OP[]BuyOrderController[]cancel[]openid:{},orderId",openid,orderId);
        try{
            Response<OrderDto> response = buyerService.cancelOrderOne(orderId, openid);
            return Response.ok(Boolean.TRUE);
        }catch (Exception e){
            log.info("OP[]BuyOrderController[]cancel[]cencal order fail");
            return Response.fail(ErrorMessage.CANCEL_ORDER_FAIL.toString());
        }
    }
}

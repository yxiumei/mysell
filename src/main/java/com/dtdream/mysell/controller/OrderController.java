package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.service.OrderMasterService;
import com.dtdream.mysell.service.OrderService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * B端订单操作
 * @Author yxiumei
 * @Data 2018/11/24 20:54
 */
@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView findOrderList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                      Map<String,Object> map){
        Response<PageInfo<OrderDto>> all = orderMasterService.findAll(page, pageSize);
        map.put("orderPage", all.getData());
        map.put("currentPage", page);
        map.put("size", pageSize);
        return new ModelAndView("order/list",map);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    @GetMapping(value = "/cancel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView cancel(@RequestParam String orderId,Map<String,Object> map){
        if (StringUtils.isEmpty(orderId)){
            log.error("OP[]BuyerProductController[]cencal[]order id is null");
            map.put("error",ErrorMessage.CANCEL_ORDER_FAIL.toString());
        }
        Response<OrderDto> one = orderService.findOne(orderId);
        if (!one.isSuccess() || ObjectUtils.isEmpty(one.getData())){
            log.error("OP[]BuyerProductController[]cencal[]get order fail");
            map.put("error",ErrorMessage.GET_ORDER_DETAIL_FAIL.toString());
        }
        Response<OrderDto> cancel = orderService.cancel(one.getData());
        if (!cancel.isSuccess() || ObjectUtils.isEmpty(cancel.getData())){
            map.put("error",ErrorMessage.CANCEL_ORDER_FAIL.toString());
        }
        // 在请求数据，用作展示
        Response<PageInfo<OrderDto>> all = orderMasterService.findAll(1, 10);
        map.put("orderPage", all.getData());
        map.put("currentPage", 1);
        map.put("size", 10);
        return new ModelAndView("order/list",map);
    }
}

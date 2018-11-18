package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.service.OrderService;
import com.dtdream.mysell.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 支付
 * @Author yxiumei
 * @Data 2018/11/18 14:59
 */
@Controller
public class PayController {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;

    /**
     * 支付
     * @param orderId
     * @param returnUrl
     * @param map
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map){
        Response<OrderDto> one = orderService.findOne(orderId);
        if (null == one.getData()){
            throw  new RuntimeException();
        }
        Response<PayResponse> payResponseResponse = payService.create(one.getData());
        map.put("payRespnse",payResponseResponse.getData());
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 微信异步通知
     * @param notify
     * @return
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notify){
        payService.notify(notify);
        return new ModelAndView("pay/success");
    }
}

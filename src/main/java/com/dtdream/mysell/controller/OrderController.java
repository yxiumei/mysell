package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.service.OrderMasterService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @Author yxiumei
 * @Data 2018/11/24 20:54
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMasterService orderMasterService;

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

    @GetMapping("/list1")
    @ResponseBody
    public List<OrderDto> findOrderList1(@RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "pageSiz",defaultValue = "10") Integer pageSize,
                               Map<String,Object> map){
        Response<PageInfo<OrderDto>> all = orderMasterService.findAll(page, pageSize);
        map.put("orderPage", all.getData());
        map.put("currentPage", page);
        return all.getData().getList();
    }
}

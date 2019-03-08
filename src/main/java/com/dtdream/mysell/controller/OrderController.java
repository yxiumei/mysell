package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.dto.SalesAmountDto;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.model.OrderDetail;
import com.dtdream.mysell.service.OrderMasterService;
import com.dtdream.mysell.service.OrderService;
import com.dtdream.mysell.utils.ExcelUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView findOrderList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                      @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                      Map<String,Object> map){
        Response<PageInfo<OrderDto>> all = orderMasterService.findAll(page, pageSize);
        // 查询销售额信息
        Response<SalesAmountDto> salesAount = orderMasterService.getSalesAount();
        map.put("orderPage", all.getData());
        map.put("currentPage", page);
        map.put("size", pageSize);
        map.put("salesAount", salesAount.getData());
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
        // 退款
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

    /**
     * 获取订单详情
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping(value = "/detail/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView orderDetail(@PathVariable String orderId, Map<String, Object> map){
        try{
            if (StringUtils.isEmpty(orderId)){
                map.put("msg","error");
                log.error("OP[]OrderController[]orderDetail[]orderId is null");
                return new ModelAndView("/order/detail",map);
            }
            Response<OrderDto> one = orderService.findOne(orderId);
            OrderDto orderDto = one.getData();
            if (!one.isSuccess() || null == orderDto) {
                log.error("OP[]OrderController[]orderDetail[]order not exit");
                map.put("msg","error");
                return new ModelAndView("/order/detail",map);
            }
            List<OrderDetail> list = orderService.orderDetails(orderId);
            orderDto.setOrderDetails(list);
            Integer sum = 0;
            // 计算订单总价
            for (OrderDetail orderDetail : list){
                Integer productQuantity = orderDetail.getProductQuantity();
                sum = sum + orderDetail.getProductPrice().intValue() * productQuantity;
            }
            map.put("orderDto",orderDto);
            map.put("orderId",orderId);
            map.put("totalAmount",sum);
            return new ModelAndView("/order/detail",map);
        }catch (Exception e){
            log.error("OP[]OrderController[]orderDetail[]get order detail fail");
            map.put("msg","获取订单详情失败");
            return new ModelAndView("/error",map);
        }

    }

    /**
     * 接收订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping(value = "/finish/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView finish(@PathVariable String orderId,Map<String,Object> map){
        try {
            if (StringUtils.isEmpty(orderId)){
                log.error("OP[]OrderController[]orderDetail[]order is null");
                map.put("msg","订单不存在！");
                return new ModelAndView("/error",map);
            }
            Response<OrderDto> one = orderService.findOne(orderId);
            Response<OrderDto> finish = orderService.finish(one.getData());
            if (!finish.isSuccess() || null == finish.getData()){
                log.error("OP[]OrderController[]orderDetail[]finsh order result:{}",finish);
                map.put("msg","完结订单失败！");
                return new ModelAndView("/error",map);
            }
            return new ModelAndView("redirect:/order/list");
        }catch (Exception e){
            log.error("OP[]OrderController[]orderDetail[]finsh error:{}",e.getStackTrace());
            map.put("msg","完结订单失败！");
            return new ModelAndView("/error",map);
        }
    }

    /**
     * 导出订单数据
     * @param response
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) {
        String excelName = "订单数据";
        String[] columnName = new String[]{"订单号","姓名","手机号","地址","金额","订单状态","支付状态","创建时间","修改时间"};
        String[] keys = new String[]{"orderId","buyerName","buyerPhone","buyerAddress","orderAmount","orderStatusStr",
                "payStatusStr","createTime","updateTime"};
        try {
            Response<List<OrderDto>> all = orderService.findAll();
            if (all.isSuccess() && !CollectionUtils.isEmpty(all.getData())) {
                List<Map<String, Object>> datas = new ArrayList<>();
                List<OrderDto> data = all.getData();
                for (OrderDto orderDto : data) {
                    Map<String, Object> map = new HashMap(16);
                    map.put("orderId",orderDto.getOrderId());
                    map.put("buyerName", orderDto.getBuyerName());
                    map.put("buyerPhone", orderDto.getBuyerPhone());
                    map.put("buyerAddress", orderDto.getBuyerAddress());
                    map.put("orderAmount", orderDto.getOrderAmount().toString());
                    map.put("orderStatusStr", orderDto.getOrderStatusStr());
                    map.put("payStatusStr", orderDto.getPayStatusStr());
                    map.put("createTime", FORMAT.format(orderDto.getCreateTime()));
                    map.put("updateTime", FORMAT.format(orderDto.getUpdateTime()));
                    datas.add(map);
                }
                Workbook workBook = ExcelUtil.createWorkBook(excelName, datas, keys, columnName);
                response.setHeader("content-Type", "application/vnd.ms-excel");
                //默认Excel名称
                String filename= "订单列表";
                response.setHeader("Content-disposition", "attachment;filename="
                        + new String(filename.getBytes("gbk"), "iso8859-1")+".xls");
                //response.setHeader("Content-disposition", "attachment;filename=订单列表.xls");
                response.flushBuffer();
                ServletOutputStream outputStream = response.getOutputStream();
                workBook.write(outputStream);
                workBook.close();
                outputStream.close();
            }
    } catch (Exception e){
        e.printStackTrace();
    }
    }
}

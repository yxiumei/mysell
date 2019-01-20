package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.enums.OrderStatusEnum;
import com.dtdream.mysell.enums.PayStatusEum;
import com.dtdream.mysell.manage.OrderManage;
import com.dtdream.mysell.mapper.OrderDetailMapper;
import com.dtdream.mysell.mapper.OrderMasterMapper;
import com.dtdream.mysell.mapper.ProductInfoMapper;
import com.dtdream.mysell.model.OrderDetail;
import com.dtdream.mysell.model.OrderMaster;
import com.dtdream.mysell.model.ProductInfo;
import com.dtdream.mysell.service.OrderService;
import com.dtdream.mysell.service.PayService;
import com.dtdream.mysell.utils.KeyUtil;
import com.dtdream.mysell.utils.OrderMaster2OrderDtoConverter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired(required = false)
    private OrderMasterMapper orderMasterMapper;
    @Autowired(required = false)
    private OrderDetailMapper orderDetailMapper;
    @Autowired(required = false)
    private ProductInfoMapper productInfoMapper;
    @Autowired(required = false)
    private OrderManage orderManage;
    @Autowired
    private PayService payService;


    @Override
    public Response create(OrderDto orderDto) {
        try{
            String orderId = KeyUtil.getUniqueKey();
            // 总价
            BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
            if (StringUtils.isEmpty(orderDto)){
                log.error("OP[]service[]OrderServiceImpl[]create[]param is null");
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            List<OrderDetail> orderTetails = new ArrayList<>();
            for (OrderDetail orderTetail: orderDto.getOrderDetails()) {
                ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(orderTetail.getProductId());
                if (productInfo == null){
                    return Response.fail(ErrorMessage.PRODUCT_INFO_IS_NULL.toString());
                }
                // 计算订单总价
                orderAmount = productInfo.getProductPrice()
                        .multiply(new BigDecimal(orderTetail.getProductQuantity()))
                        .add(orderAmount);
                orderTetail.setDetailId(KeyUtil.getUniqueKey());
                orderTetail.setOrderId(orderId);
                BeanUtils.copyProperties(productInfo, orderTetail);
                // 订单详情数据
                orderTetails.add(orderTetail);
            }
            // 主订单数据
            OrderMaster orderMaster = new OrderMaster();
            BeanUtils.copyProperties(orderDto, orderMaster);
            orderMaster.setOrderId(orderId);
            orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
            orderMaster.setPayStatus(OrderStatusEnum.NEW.getCode());
            orderMaster.setOderAmount(orderAmount);
            // 扣库存数据
            List<CartDto> cartDtoList = orderDto.getOrderDetails().stream()
                    .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                    .collect(Collectors.toList());
            orderManage.createOrder(orderTetails, orderMaster, cartDtoList);
            Map<String, String> map = new HashMap<>();
            map.put("orderId",orderId);
            return Response.ok(map);
        }catch (Exception e){
            log.error("OP[]service[]OrderServiceImpl[]create[]create order fail:{}",e.fillInStackTrace());
            return Response.fail(ErrorMessage.CREATE_ORDER_FAIL.toString());
        }

    }

    @Override
    public Response<OrderDto> findOne(String orderId) {
        OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
        if (null == orderMaster){
            return Response.fail(ErrorMessage.GET_PRODUCT_MASTER_FAIL.toString());
        }
        List<OrderDetail> detailList = orderDetailMapper.findByOrder(orderId);
        if (StringUtils.isEmpty(detailList)){
            return Response.fail(ErrorMessage.ORDERDETAIL_NOT_EXIST.toString());
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderAmount(orderMaster.getOderAmount());
        orderDto.setOrderDetails(detailList);
        return Response.ok(orderDto);
    }

    @Override
    public Response<PageInfo<OrderDto>> findOrderList(Integer pageNo, Integer pageSize, String buyerOpenId) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);
        List<OrderMaster> byBuyOpenId = orderMasterMapper.findByBuyOpenId(buyerOpenId);
        List<OrderDto> orderDtos = OrderMaster2OrderDtoConverter.convert(byBuyOpenId);
        PageInfo<OrderDto> pageInfoOrder = new PageInfo<>(orderDtos);
        return Response.ok(pageInfoOrder);
    }

    @Override
    public Response<OrderDto> cancel(OrderDto orderDto) {
        try{
            OrderMaster orderMaster = new OrderMaster();
            // 订单是否是新单
            if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
                log.error("OP[]service[]OrderServiceImpl[]cancel[]oderStatus={},orderId={}",
                        orderDto.getOrderStatus(), orderDto.getOrderId());
                return Response.fail(ErrorMessage.ORDER_PARAM_ERROR.toString());
            }
            // 更改状态
            orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
            BeanUtils.copyProperties(orderDto, orderMaster);
            List<CartDto> cartDtos = orderDto.getOrderDetails().stream()
                    .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                    .collect(Collectors.toList());
            orderManage.cancel(orderMaster, cartDtos);
            // 如果订单已支付，去退款
            if (PayStatusEum.SUCCESS.getCode().equals(orderDto.getPayStatus())){
                payService.imitateRefund(orderDto);
            }
            return Response.ok(orderDto);
        }catch (Exception e){
            log.error("OP[]service[]OrderServiceImpl[]cancel[]cancel order fail:{}",e.fillInStackTrace());
            return Response.fail(ErrorMessage.CANCEL_ORDER_FAIL.toString());
        }
    }

    @Override
    public Response<OrderDto> finish(OrderDto orderDto) {
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("OP[]service[]OrderServiceImpl[]finish[]oderStatus={},orderId={}",
                    orderDto.getOrderStatus(), orderDto.getOrderId());
            return Response.fail(ErrorMessage.ORDER_PARAM_ERROR.toString());
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        Integer result = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        if (result != 1){
            log.error("OP[]service[]OrderServiceImpl[]finish[] finish order fail");
            return Response.fail(ErrorMessage.FINISH_ORDER_FAIL.toString());
        }
        return Response.ok(orderDto);
    }


    @Override
    public Response paidOrder(OrderDto orderDto) {
        // todo 这里，当 先接单，后支付，会出现bug
        if (!OrderStatusEnum.NEW.getCode().equals(orderDto.getOrderStatus())){
            log.error("OP[]service[]OrderServiceImpl[]paidOrder[]payStatus={},orderId={}",
                    orderDto.getOrderStatus(), orderDto.getOrderId());
            return Response.fail(ErrorMessage.ORDER_PARAM_ERROR.toString());
        }
        // 判断支付状态
        if (!PayStatusEum.WAIT.getCode().equals(orderDto.getPayStatus())){
            log.error("OP[]service[]OrderServiceImpl[]paidOrder[]payStatus={},orderId={}",
                    orderDto.getPayStatus(), orderDto.getOrderId());
            return Response.fail(ErrorMessage.ORDER_PAY_STATUS_ERROR.toString());
        }
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setPayStatus(PayStatusEum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        Integer result = orderMasterMapper.updateByPrimaryKeySelective(orderMaster);
        if (1 != result){
            log.error("OP[]service[]OrderServiceImpl[]paidOrder[] paidOrder fail");
            return Response.fail(ErrorMessage.FINISH_ORDER_FAIL.toString());
        }
        return Response.ok(orderDto);
    }

    @Override
    public List<OrderDetail> orderDetails(String orderId) {
        return orderDetailMapper.findByOrder(orderId);
    }

}

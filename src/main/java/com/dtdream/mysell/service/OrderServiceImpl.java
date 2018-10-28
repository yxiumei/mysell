package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.OrderDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.enums.OrderStatusEnum;
import com.dtdream.mysell.manage.OrderManage;
import com.dtdream.mysell.mapper.OrderDetailMapper;
import com.dtdream.mysell.mapper.OrderMasterMapper;
import com.dtdream.mysell.mapper.ProductInfoMapper;
import com.dtdream.mysell.model.OrderDetail;
import com.dtdream.mysell.model.OrderMaster;
import com.dtdream.mysell.model.ProductInfo;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨秀眉
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterMapper orderMasterMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private OrderManage orderManage;

    @Override
    public Response<OrderDto> create(OrderDto orderDto) {
        try{
            String orderId = KeyUtil.getUniqueKey();
            // 总价
            BigDecimal orderAmount = new BigDecimal(BigInteger.ONE);
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
            return Response.ok(orderDto);
        }catch (Exception e){
            log.error("OP[]service[]OrderServiceImpl[]create[]create order fail:{}",e);
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
        return null;
    }

    @Override
    public Response<OrderDto> fainish(OrderDto orderDto) {
        return null;
    }

    @Override
    public Response<OrderDto> paidOrder(OrderDto orderDto) {
        return null;
    }
}

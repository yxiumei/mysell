package com.dtdream.mysell.manage;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.mapper.ProductInfoMapper;
import com.dtdream.mysell.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yxiumei
 */
@Component
@Slf4j
public class ProductManage {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    /**
     * 加库存
     * @param cartDtoList
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> increaseStock(List<CartDto> cartDtoList) throws Exception {
        for (CartDto cartDto : cartDtoList){
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartDto.getProductId());
            if (null == productInfo){
                log.error("OP[]ProductManage[]increaseStock[]productInfo is null");
                throw new Exception();
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            Integer i = productInfoMapper.updateByPrimaryKeySelective(productInfo);
        }
        return Response.ok(Boolean.TRUE);
    }

    /**
     * 减库存
     * @param cartDtoList
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Response<Boolean> decreaseStock(List<CartDto> cartDtoList) throws Exception {
        for (CartDto cartDto: cartDtoList){
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartDto.getProductId());
            if (null == productInfo){
                log.error("OP[]ProductManage[]increaseStock[]productInfo is null");
                throw new Exception();
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0){
                Response.fail(ErrorMessage.INVENTORY_SHORTAGE.toString());
            }
            productInfo.setProductStock(result);
            Integer i = productInfoMapper.updateByPrimaryKeySelective(productInfo);
            if (i <= 0){
                Response.fail(ErrorMessage.UPDATE_PRODUCT_STOCK_FAIL.toString());
            }
        }
        return Response.ok(Boolean.TRUE);
    }
}

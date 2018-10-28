package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.Response;

import java.util.List;

/**
 * 商品
 * @author 杨秀眉
 */
public interface ProductService {

    /**
     * 查询所有上架商品
     */
    Response findUpFrameProduct();

    /**
     * 加库存
     * @param cartDtoList
     * @return
     */
    Response<Boolean> increaseStock(List<CartDto> cartDtoList);

    /**
     * 扣库存
     * @param cartDtoList
     * @return
     */
    Response<Boolean> decreaseStock(List<CartDto> cartDtoList);
}

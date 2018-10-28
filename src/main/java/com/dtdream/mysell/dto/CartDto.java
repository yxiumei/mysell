package com.dtdream.mysell.dto;

import lombok.Data;

/**
 * 购物车
 * @author 杨秀眉
 */
@Data
public class CartDto {

    /** 商品id */
    private String productId;

    /** 数量 */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}

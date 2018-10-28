package com.dtdream.mysell.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    GET_UP_FRAME_PRODUCT_FAIL_("get.up.frame.product.fail"), // 获取上架商品失败
    GET_ALL_CATEGROY_FAIL("get.all.category.fail"),   // 获取所欲类目失败
    PARAM_IS_NULL("param.is.null"),
    PRODUCT_INFO_IS_NULL("product.info.is.null"),
    CREATE_ORDER_FAIL("create.order.fail"),
    PRODUCT_NOT_EXIST("product.not.exist"),  // 商品不存在
    INVENTORY_SHORTAGE("inventory.shortage"), // 库存不足
    UPDATE_PRODUCT_STOCK_FAIL("update,product.stock.fail"), // 更新商品库存失败
    ;

    private String messages;

    ErrorMessage(String messages) {
        this.messages = messages;
    }
}

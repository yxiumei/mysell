package com.dtdream.mysell.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {

    GET_UP_FRAME_PRODUCT_FAIL_("get.up.frame.product.fail"), // 获取上架商品失败
    GET_ALL_CATEGROY_FAIL("get.all.category.fail"),   // 获取所欲类目失败
    PARAM_IS_NULL("param.is.null"),          // 参数是空
    PRODUCT_INFO_IS_NULL("product.info.is.null"),  // 商品信息是空
    CREATE_ORDER_FAIL("create.order.fail"),        // 创建订单失败
    PRODUCT_NOT_EXIST("product.not.exist"),  // 商品不存在
    INVENTORY_SHORTAGE("inventory.shortage"), // 库存不足
    UPDATE_PRODUCT_STOCK_FAIL("update,product.stock.fail"), // 更新商品库存失败
    GET_PRODUCT_MASTER_FAIL("get.product.master.fail"),   // 获取商品信息失败
    ORDERDETAIL_NOT_EXIST("orderdetail.not.exist"),       // 订单详情没有存在
    ;

    private String messages;

    ErrorMessage(String messages) {
        this.messages = messages;
    }
}

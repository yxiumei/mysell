package com.dtdream.mysell.enums;

import lombok.Getter;

/**
 * @author yxiumei
 */

@Getter
public enum ErrorMessage {

    GET_UP_FRAME_PRODUCT_FAIL_("get.up.frame.product.fail"), /* 获取上架商品失败 */
    GET__PRODUCT_LIST_FAIL_("get.product.list.fail"), /* 获取商品列表失败 */
    GET_ALL_CATEGROY_FAIL("get.all.category.fail"),   /* 获取所欲类目失败*/
    PARAM_IS_NULL("param.is.null"),          /* 参数是空*/
    PRODUCT_INFO_IS_NULL("product.info.is.null"),  /*商品信息是空*/
    CREATE_ORDER_FAIL("create.order.fail"),        /* 创建订单失败*/
    GET_ORDER_LIST_FAIL("get.order.list.fail"),        /* 获得订单列表失败*/
    GET_ORDER_DETAIL_FAIL("get.order.detail.fail"),        /*获得订单详情失败*/
    OPENID_NO_EQUEAL("openid.no.equal"),        /* openid不相等*/
    PRODUCT_NOT_EXIST("product.not.exist"),  /*商品不存在 */
    INVENTORY_SHORTAGE("inventory.shortage"), /* 库存不足 */
    UPDATE_PRODUCT_STOCK_FAIL("update,product.stock.fail"), /* 更新商品库存失败*/
    GET_PRODUCT_MASTER_FAIL("get.product.master.fail"),   /* 获取商品信息失败*/
    ORDERDETAIL_NOT_EXIST("orderdetail.not.exist"),       /* 订单详情没有存在*/
    ORDER_PARAM_ERROR("order.param.error"),       /*订单参数错误*/
    ORDER_PAY_STATUS_ERROR("order.pay.status.error"),       /*订单支付状态错误*/
    CANCEL_ORDER_FAIL(" cancel.order.fail"),   /* 取消订单失败*/
    FINISH_ORDER_FAIL(" finish.order.fail"),  /*  完结订单失败*/
    PAY_ORDER_FINISH_FAIL("pay.order.finish.fail"),  /* 完结订单失败*/
    CART_IS_NULL("cart.is.null"),  /* 购物车是空*/
    ORDER_AMOUNT_DIFFER("order.amount.differ"),  /* 订单金额不一致*/
    AWEIXIN_NOTIFU_FAIL("weixin.notifu.fail"),  /* 微信通知失败*/
    ORDER_PAY_FAIL("order.pay.fail"), /* 订单支付失败 */
    WEIXIN_REFUND_FAIL("weixin.refund.fail"); /* 微信退款失败 */
    ;

    /**
     * 错误信息
     */
    private String messages;

    /**
     * @param messages
     */
    ErrorMessage(String messages) {
        this.messages = messages;
    }
}

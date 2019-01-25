package com.dtdream.mysell.utils;

import com.dtdream.mysell.config.WechatAccountConfig;
import com.dtdream.mysell.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author yxiumei 微信推送消息
 * @Data 2019/1/25 21:35
 */
@Slf4j
@Component
public class WechatSendInfoUtils{

    @Autowired
    private WxMpService wxService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    public void sendKefuMessage(OrderDto orderDto) {
        try {
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setTemplateId(wechatAccountConfig.getTemplateId());
            templateMessage.setToUser(orderDto.getBuyerOpenid());
            List<WxMpTemplateData> data = Arrays.asList(
                    new WxMpTemplateData("businessName", "小杨美食店"),
                    new WxMpTemplateData("businessTelephone", "18785528804"),
                    new WxMpTemplateData("orderId", orderDto.getOrderId()),
                    new WxMpTemplateData("status", "支付成功，对方以收款"),
                    new WxMpTemplateData("payMoney", "$"+ orderDto.getOrderAmount()),
                    new WxMpTemplateData("remark", "欢迎再次光临！")
            );
            templateMessage.setData(data);
            String result = wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            log.error("OP[]WechatSendInfoUtils[]send info result:{}", result);
        } catch (Exception e) {
            log.error("OP[]WechatSendInfoUtils[]send info error:{}",e.getStackTrace());
        }
    }


}

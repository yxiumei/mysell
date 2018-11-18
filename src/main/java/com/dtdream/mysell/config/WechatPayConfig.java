package com.dtdream.mysell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author yxiumei
 * @Data 2018/11/18 21:08
 */
@Component
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bastPayServiceImp() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wph5 = new WxPayH5Config();
        wph5.setAppId(wechatAccountConfig.getMpAppId());
        wph5.setAppSecret(wechatAccountConfig.getMpAppId());
        wph5.setMchId(wechatAccountConfig.getMchId());
        wph5.setMchKey(wechatAccountConfig.getMchKey());
        wph5.setKeyPath(wechatAccountConfig.getKeyPath());
        wph5.setNotifyUrl(wechatAccountConfig.getNotifyUrl());
        return wph5;
    }

}

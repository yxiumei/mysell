package com.dtdream.mysell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author yxiumei
 * @Data 2018/11/18 21:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig{

    private String mpAppId;
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;
    /**
     * 商户秘钥
     */
    private String mchKey;
    /**
     * 商户证书路劲
     */
    private String keyPath;
    private String notifyUrl;
}

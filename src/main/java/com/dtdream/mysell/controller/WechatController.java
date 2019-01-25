package com.dtdream.mysell.controller;

import com.dtdream.mysell.config.WechatAccountConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @Author yxiumei
 * @Data 2018/11/11 23:14
 */
@RequestMapping("/wechat")
@Slf4j
@Controller
public class WechatController {

    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @GetMapping("/authorize")
    public String auth(@RequestParam("returnUrl") String returnUrl){
        // 重定向url
        String notifyUrl = wechatAccountConfig.getNotifyUrl();
        // 调用方法
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(notifyUrl,WxConsts.OAuth2Scope.SNSAPI_USERINFO,
                URLEncoder.encode(returnUrl));
        log.info("WechatController[]WechatController[]微信网页授权获得code,result={}",redirectUrl);
        return  "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl) {
        log.info("WechatController[]WechatController[]userInfo[]微信网页授权获得code={},returnUrl:{}", code,returnUrl);
        try {
            // 获取token
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            log.info("echatController[]WechatController[]userInfo【获得用户openid】=",wxMpOAuth2AccessToken.getOpenId());
            // 获取用户信息
            WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            log.info("WechatController[]WechatController[]userInfo【获得用户信息】={},【openid】={}",
                    wxMpUser, wxMpOAuth2AccessToken.getOpenId());
            // 获取opeinId
            String openId = wxMpOAuth2AccessToken.getOpenId();
            return "redirect:" + returnUrl + "?openid=" + openId;
        } catch (WxErrorException e) {
            log.error("WechatController[]WechatController[]userInfo[]微信网页授权异常：{}",e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}


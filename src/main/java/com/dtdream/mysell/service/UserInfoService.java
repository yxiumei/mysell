package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.UserInfo;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @Author yxiumei
 * @Data 2019/2/4 12:42
 */
public interface UserInfoService {

    /**
     * 插入用户信息
     * @param wxMpUser 用户信息
     * @return
     */
    Response<Boolean> insert(WxMpUser wxMpUser);

    /**
     * 用过openId查询用户信息
     * @param openId
     * @return
     */
    Response<UserInfo> findUserInfoByOpenId(String openId);
}

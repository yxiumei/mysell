package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.enums.UserType;
import com.dtdream.mysell.mapper.UserInfoMapper;
import com.dtdream.mysell.model.UserInfo;
import com.dtdream.mysell.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 用户信息
 * @Author yxiumei
 * @Data 2019/2/4 12:44
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;

    @Override
    public Response<Boolean> insert(WxMpUser wxMpUser) {
        try {
            log.info("OP[]UserInfoServiceImpl[]insert[]insert user info WxMpUser:{}", wxMpUser);
            if (null == wxMpUser || wxMpUser.getOpenId() == null) {
                log.error("OP[]UserInfoServiceImpl[]insert[]user info is null");
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            // 判断该用户是否存在
            UserInfo user = userInfoMapper.selectByOpenId(wxMpUser.getOpenId());
            if (null != user) {
                log.info("OP[]UserInfoServiceImpl[]insert[]user exist!");
                return Response.ok(Boolean.TRUE);
            }
            UserInfo userInfo = new UserInfo();
            userInfo.setOpenid(wxMpUser.getOpenId());
            userInfo.setUsername(wxMpUser.getNickname());
            userInfo.setUserImg(wxMpUser.getHeadImgUrl());
            userInfo.setUserType(UserType.ORDINARY_USERS.getCode());
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            int insert = userInfoMapper.insert(userInfo);
            if (insert != 1){
                log.error("OP[]UserInfoServiceImpl[]insert[]insert user info fail");
                return Response.fail(ErrorMessage.SAVE_USER_INFO_FAIL.toString());
            }
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            log.error("OP[]UserInfoServiceImpl[]insert[]insert user info fail:{}",e.getStackTrace());
            return Response.fail(ErrorMessage.SAVE_USER_INFO_FAIL.toString());
        }

    }

    @Override
    public Response<UserInfo> findUserInfoByOpenId(String openId) {
        try {
            if (StringUtils.isEmpty(openId)) {
                log.error("OP[]UserInfoServiceImpl[]findUserInfoByOpenId[]open id is null");
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            UserInfo userInfo = userInfoMapper.selectByOpenId(openId);
            if (null == userInfo) {
                log.error("OP[]UserInfoServiceImpl[]findUserInfoByOpenId[]openId:{},find userInfo is null",openId);
                return Response.fail(ErrorMessage.PARAM_INVALID.toString());
            }
            return Response.ok(userInfo);
        } catch (Exception e){
            log.error("OP[]UserInfoServiceImpl[]findUserInfoByOpenId[]openId:{}find user info fail:{}",openId,e.fillInStackTrace());
            return Response.fail(ErrorMessage.FIND_USER_INFO_FAIL.toString());
        }
    }
}

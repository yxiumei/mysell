package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息
 * @Author yxiumei
 * @Data 2019/2/4 12:25
 */
public interface UserInfoMapper {

    /**
     * 插入用户信息
     * @param userInfo 用户信息
     * @return
     */
    int insert(UserInfo userInfo);

    /**
     * 通过用户id查询
     * @param userId 用户id
     * @return
     */
    UserInfo selectByPrimaryKey(@Param("userId") Integer userId);

    /**
     * 通过openId查询用户线信息
     * @param openId 微信用户标识
     * @return
     */
    UserInfo selectByOpenId(@Param("openId") String openId);

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return
     */
    UserInfo findUserByUserName(@Param("username") String username);

}

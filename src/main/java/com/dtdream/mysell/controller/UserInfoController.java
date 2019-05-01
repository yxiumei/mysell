package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.UserInfo;
import com.dtdream.mysell.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Describe
 * @Authour yxiumei
 * @Date 2019/4/28
 */
@Slf4j
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView login(@RequestParam String userName, @RequestParam String password,
                              HttpServletRequest request, Map<String, Object> map) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            log.error("OP[]UserInfoController[]userName:{}, password:{}", userName, password);
            return null;
        }
        Response<UserInfo> userInfo = userInfoService.findUserInfoByUserNameAndPassWord(userName, password);
        if (!userInfo.isSuccess()) {
            map.put("error", userInfo.getMsg());
        }
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", userInfo.getData());
        return new ModelAndView("redirect:/order/list");
    }

    @GetMapping("/index")
    public ModelAndView toLogin(){
        return new ModelAndView("/login");
    }

    @GetMapping(value = "/loginOut")
    private ModelAndView loginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if (null != userInfo) {
            session.setAttribute("userInfo", null);
        }
        return new ModelAndView("/login");
    }
}

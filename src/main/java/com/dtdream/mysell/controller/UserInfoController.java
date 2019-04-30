package com.dtdream.mysell.controller;

import com.dtdream.mysell.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Describe
 * @Authour yxiumei
 * @Date 2019/4/28
 */
@Slf4j
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView login(@RequestParam String userName, @RequestParam String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            log.error("OP[]UserInfoController[]userName:{}, password:{}", userName, password);
            return null;
        }
        return null;

    }
}

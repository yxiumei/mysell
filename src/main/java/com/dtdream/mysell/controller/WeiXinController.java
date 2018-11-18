package com.dtdream.mysell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yxiumei
 * @Data 2018/11/11 23:14
 */
@RequestMapping("/weixin")
@Slf4j
@RestController
public class WeiXinController {


    @GetMapping("/auth")
    public void auth(@RequestParam String code){

    }
}


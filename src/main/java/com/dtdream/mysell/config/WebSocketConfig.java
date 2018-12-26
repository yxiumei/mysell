package com.dtdream.mysell.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * webSocket配置类
 * @Author yxiumei
 * @Data 2018/12/26 22:06
 */
@Component
public class WebSocketConfig {

    @Bean
    private ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}

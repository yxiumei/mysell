package com.dtdream.mysell.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 *  js中事件方法实现
 * @Author yxiumei
 * @Data 2018/12/26 22:20
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();

    /**
     * 建立连接
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this);
        log.info("【新建连接】，连接总数:{}", webSockets.size());
    }

    /**
     * 断开连接
     */
    @OnClose
    public void onClose(){
        webSockets.remove(this);
        log.info("【断开连接】，连接总数:{}", webSockets.size());
    }

    /**
     * 接收到信息
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        webSockets.remove(this);
        log.info("【收到】，客户端的信息:{}", message);
    }

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(String message){
        log.info("【广播发送】，信息:{}，总连接数:{}", message, webSockets.size());
        for (WebSocket webSocket : webSockets) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.info("【广播发送】，信息异常:{}", e.fillInStackTrace());
            }
        }
    }
}

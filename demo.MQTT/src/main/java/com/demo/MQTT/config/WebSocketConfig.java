package com.demo.MQTT.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@Configuration
public class WebSocketConfig {
    private ZContext context;
    private String addr = "tcp://localhost:8081";
    private ZMQ.Socket server;

    public WebSocketConfig() {
        this.context = new ZContext();
        this.server = context.createSocket(SocketType.XPUB);
        this.server.bind(addr);
    }

    public void sendMessage(String message) {
        // 메시지를 보내는 코드 (메시지 정의에 따라 구현 필요)
        server.send(message, 0);
        System.out.println("내부전송완료");

    }

    public void close() {
        // 리소스 정리
        server.close();
        context.close();
    }

    public static void main(String[] args) {
        WebSocketConfig handler = new WebSocketConfig();
        // 사용 예시: handler.sendMessage("Hello, World!");
        handler.close(); // 프로그램 종료 시 꼭 호출
    }
}

package com.demo.MQTT.service;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
public class ZeroMq_sub {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // SUB 소켓 생성
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);

            // XPUB 소켓과 연결
            subscriber.connect("tcp://localhost:8081");
            subscriber.subscribe(ZMQ.SUBSCRIPTION_ALL);

            while (!Thread.currentThread().isInterrupted()) {
                // 메시지 수신 및 출력
                String message = subscriber.recvStr();
                System.out.println("Received: " + message);
            }
        }
    }
}

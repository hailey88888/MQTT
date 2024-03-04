package com.demo.MQTT.service;

import com.demo.MQTT.config.WebSocketConfig;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttService_pub_sub {

    @Autowired
    private IMqttClient mqttClient;

    @Autowired
    private WebSocketConfig webSocket;

    public void publish(final String topic, final String payload, int qos, boolean retained)
        throws MqttPersistenceException, MqttException{
        System.out.println("publish : create a mqttMessage");
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(payload.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(retained);

        mqttClient.publish(topic,mqttMessage);
        mqttClient.disconnect();
    }

    public void subscribe(final String topic)throws MqttException, InterruptedException{
        System.out.println("subscribe the topic");
        mqttClient.subscribeWithResponse(topic,(tpic, msg) -> {
            System.out.println("received the message");
            System.out.println(msg.getId() + " -> " + new String(msg.getPayload()));
            //받은 메시지를 내부 (로컬로 전달)프로그램으로 전달(소켓을 통해서)
          //  webSocket.sendMessage(new String(msg.getPayload()));
        });
    }
}

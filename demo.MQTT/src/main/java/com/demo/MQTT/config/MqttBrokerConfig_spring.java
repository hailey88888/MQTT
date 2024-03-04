package com.demo.MQTT.config;

import lombok.Value;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttBrokerConfig_spring {
    String clientId = "solbee";
    String hostname = "172.30.1.65";
    int port= 1883;

    @Bean
    public IMqttClient mqttClient() throws MqttException {
        System.out.println("1. start create MqttClient object");
        IMqttClient mqttClient = new MqttClient("tcp://" + hostname +":"+port, clientId);
        mqttClient.connect(mqttConnectOptions());
        System.out.println("3. MqttClient object has created");
        return mqttClient;
    }

    @Bean
    @ConfigurationProperties(prefix = "mqtt")
    public MqttConnectOptions mqttConnectOptions(){
        System.out.println("2. set up the connection option");
        return new MqttConnectOptions();
    }


}

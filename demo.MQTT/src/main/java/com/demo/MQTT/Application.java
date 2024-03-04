package com.demo.MQTT;

import com.demo.MQTT.service.MqttService_pub_sub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private MqttService_pub_sub mqttService;

	@Autowired
	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		final String topic = "roytuts/topic/event";
		mqttService.subscribe(topic);
		mqttService.publish(
				topic,
				"Hi\nsolbee",
				0,
				true);
		context.close();
	}
}


/*

1. 쓰레드 관점이해
main -> 병렬 처리(thread)
main과 별개로
프로그램 시작 시점 ~ 끝 까지 독립적으로  동작하는 프로그램이 필요함 (thread)
=> MQTT(thread)

2. 쓰레드 in Java / Spring Boot
자바에서 쓰레드 어플리케이션
스프링 프레임 워크에서 쓰레드 어플리케이션 짜는 법

3.
쓰레드 -> 싱글톤 객체(backend) <-> 프론트(restAPI)
쓰레드 -> 웹소켓  <-> 프론트

4. TCP
웹소켓이 자바 스크립트에서 생성해서 -> 커넥션을 서버로

 */



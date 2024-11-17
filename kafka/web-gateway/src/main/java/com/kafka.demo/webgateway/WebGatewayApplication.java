package com.kafka.demo.webgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class WebGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebGatewayApplication.class, args);
	}
}

package com.gbocquet.fundsprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class FundsProcessorApplication {
	public static void main(String[] args) {
		SpringApplication.run(FundsProcessorApplication.class, args);
	}
}

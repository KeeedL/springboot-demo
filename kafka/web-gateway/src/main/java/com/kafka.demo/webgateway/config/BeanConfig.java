package com.kafka.demo.webgateway.config;

import com.kafka.demo.webgateway.infra.SendBalanceActionProcessor;
import com.kafka.demo.webgateway.infra.kafka.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import transfer.money.request.TransferMoneyRequestDto;

@Configuration
public class BeanConfig {

    // Could easily implement other technical ways to push transfer money request
    // And then modify the bean created -
    // This bean configuration could be done depending on application.yml properties
    // Or through business logic (and so Bean creation will be in factory class)
    @Bean
    public SendBalanceActionProcessor sendBalanceActionProcessor(final KafkaTemplate<String, TransferMoneyRequestDto> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate);
    }
}

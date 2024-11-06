package com.gbocquet.webgateway.config;

import com.gbocquet.webgateway.infra.SendBalanceActionProcessor;
import com.gbocquet.webgateway.infra.kafka.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class BeanConfig {

    // Could easily implement other technical ways to push transfer money request
    // And then modify the bean created -
    // This bean configuration could be done depending on application.yml properties
    // Or through business logic (and so Bean creation will be in factory class)
    @Bean
    public SendBalanceActionProcessor sendBalanceActionProcessor(final KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate);
    }
}

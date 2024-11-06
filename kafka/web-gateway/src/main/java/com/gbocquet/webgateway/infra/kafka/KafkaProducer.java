package com.gbocquet.webgateway.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;
import com.gbocquet.webgateway.infra.SendBalanceActionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import static com.gbocquet.webgateway.constant.KafkaConstant.TRANSFER_MONEY_TOPIC;

public class KafkaProducer implements SendBalanceActionProcessor {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(final TransferMoneyRequestDto request) {
        logger.info("#### -> Producing message -> {}", request.toString());

        // Format should be done in other service/process
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(request);
            this.kafkaTemplate.send(TRANSFER_MONEY_TOPIC, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
            // TODO: manage
        }
    }
}

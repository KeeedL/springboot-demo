package com.kafka.demo.webgateway.infra.kafka;

import com.kafka.demo.webgateway.infra.SendBalanceActionProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import transfer.money.request.TransferMoneyRequestDto;

import static com.kafka.demo.webgateway.constant.KafkaConstant.TRANSFER_MONEY_TOPIC;

public class KafkaProducer implements SendBalanceActionProcessor {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, TransferMoneyRequestDto> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, TransferMoneyRequestDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(final TransferMoneyRequestDto request) {
        logger.info("#### -> Producing message -> {}", request.toString());
        this.kafkaTemplate.send(TRANSFER_MONEY_TOPIC, request);
    }
}

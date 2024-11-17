package com.kafka.demo.fundsprocessor.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.demo.fundsprocessor.service.AccountService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import transfer.money.request.TransferMoneyRequestDto;

import static com.kafka.demo.fundsprocessor.constant.KafkaConstant.TOPIC_TRANSFER_MONEY;

@Component
public class TransferMoneyEventListener {
    private static final Logger logger = LoggerFactory.getLogger(TransferMoneyEventListener.class);
    private final AccountService accountService;

    public TransferMoneyEventListener(AccountService accountService) {
        this.accountService = accountService;
    }

    @KafkaListener(topics = {TOPIC_TRANSFER_MONEY},
            groupId = "transfer-money-group-processor",
            autoStartup = "${listen.auto.start:true}"
    )
    public void listenTransferMoneyEvent(TransferMoneyRequestDto request) {
        logger.info("#### -> Consuming message -> {}", request);
        accountService.transferMoneyToOtherAccount(request);
    }
}

package com.gbocquet.fundsprocessor.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbocquet.fundsprocessor.dto.request.TransferMoneyRequestDto;
import com.gbocquet.fundsprocessor.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.gbocquet.fundsprocessor.constant.KafkaConstant.TOPIC_TRANSFER_MONEY;

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
    public void listenTransferMoneyEvent(String jsonRequest) throws JsonProcessingException { //TODO: should use avro to handle serialization and deserialization properly
        //final var requestDto = consumerRecord.value();
        final var requestDto = new ObjectMapper().readValue(jsonRequest, TransferMoneyRequestDto.class);
        logger.info("#### -> Consuming message -> {}", requestDto);
        accountService.transferMoneyToOtherAccount(requestDto);
    }
}

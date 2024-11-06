package com.gbocquet.webgateway.controller;

import com.gbocquet.webgateway.dto.TransferMoneyRequestDto;
import com.gbocquet.webgateway.service.BalanceActionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/v1/balance",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class BalanceActionController {

    private final BalanceActionService balanceActionService;

    public BalanceActionController(BalanceActionService balanceActionService) {
        this.balanceActionService = balanceActionService;
    }

    @PutMapping("transfer")
    public ResponseEntity<Void> transferMoneyRequest(@RequestBody final TransferMoneyRequestDto request) {
        balanceActionService.transferMoney(request);
        return ResponseEntity.accepted().build();
    }
}

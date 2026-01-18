package com.noobcodezz.spring_ledger.controller;

import com.noobcodezz.spring_ledger.models.*;
import com.noobcodezz.spring_ledger.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
//@RequestMapping(/)\
@RequiredArgsConstructor
public class LoanEntryController {
    private final LoanService loanService;
    @PostMapping("v1/loan")
    //ResponseEntity
    public ResponseEntity<String> createLoan(@RequestBody LoanCreationDto loanCreationDto) {
        String loanRefId = loanService.createLoan(loanCreationDto);
        return ResponseEntity.ok().body(loanRefId);
    }

    @PostMapping("v1/payment")
    public ResponseEntity<String> createPayment(@RequestBody RecordPaymentDto recordPaymentDto) {
        loanService.makePayment(recordPaymentDto);
        return ResponseEntity.ok().body("payment made successfully");
    }

    @GetMapping("v1/balance")
    public ResponseEntity<String> getBalance(@RequestParam String loanRefId,
                                             @RequestParam int emiNumber) {
        RequestBalanceDto requestBalanceDto = new RequestBalanceDto();
        requestBalanceDto.setLoanRefId(loanRefId);
        requestBalanceDto.setEmiNumber(emiNumber);
        String balance = loanService.fetchBalance(requestBalanceDto);
        return ResponseEntity.ok().body(balance);
    }
 }

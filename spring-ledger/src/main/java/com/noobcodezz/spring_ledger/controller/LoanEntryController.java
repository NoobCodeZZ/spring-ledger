package com.noobcodezz.spring_ledger.controller;

import com.noobcodezz.spring_ledger.models.*;
import com.noobcodezz.spring_ledger.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
//@RequestMapping(/)\
@RequiredArgsConstructor
public class LoanEntryController {
    private final LoanService loanService;
    @PostMapping("v1/loan")
    //ResponseEntity
    public ResponseEntity<String> createLoan(@RequestBody LoanCreationDto loanCreationDto) {
        loanService.createLoan(loanCreationDto);
        return ResponseEntity.ok().body("loan created");
    }

    @PostMapping("v1/payment")
    public ResponseEntity<String> createPayment(@RequestBody RecordPaymentDto recordPaymentDto) {
        loanService.makePayment(recordPaymentDto);
        return ResponseEntity.ok().body("payment made successfully");
    }

    @GetMapping("v1/balance")
    public ResponseEntity<String> getBalance(@RequestBody RequestBalanceDto requestBalanceDto) {
        String balance = loanService.fetchBalance(requestBalanceDto);
        return ResponseEntity.ok().body(balance);
    }
 }

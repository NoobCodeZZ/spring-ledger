package com.noobcodezz.spring_ledger.controller;

import com.noobcodezz.spring_ledger.models.LoanCreationDto;
import com.noobcodezz.spring_ledger.service.LoanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
 }

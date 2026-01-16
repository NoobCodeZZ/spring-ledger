package com.noobcodezz.spring_ledger.service;

import com.noobcodezz.spring_ledger.db.service.LoanDbService;
import com.noobcodezz.spring_ledger.models.LoanCreationDto;
import com.noobcodezz.spring_ledger.models.LoanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {

    private final LoanDbService loanDbService;

    public void createLoan(LoanCreationDto loanCreationDto) {

        // what does loan repository take as input
        LoanDto loanDto = new LoanDto();
        loanDto.setPrincipal(loanCreationDto.getPrincipal());
        loanDto.setRoi(loanCreationDto.getRoi());
        loanDto.setYears(loanCreationDto.getYears());
        loanDto.setBankRefID(loanCreationDto.getBankRefId());
        loanDto.setUserRefID(loanCreationDto.getUserRefId());
        loanDbService.createLoan(loanDto);
    }

    public void lumpSumPayment()
}

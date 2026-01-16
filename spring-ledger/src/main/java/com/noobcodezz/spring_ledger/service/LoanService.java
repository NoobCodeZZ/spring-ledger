package com.noobcodezz.spring_ledger.service;

import com.noobcodezz.spring_ledger.db.service.LoanDbService;
import com.noobcodezz.spring_ledger.db.service.PaymentDbService;
import com.noobcodezz.spring_ledger.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {

    private final LoanDbService loanDbService;
    private final PaymentDbService paymentDbService;


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

    public void makePayment(RecordPaymentDto recordPaymentDto) {
        // have to fetch the loan entity/dto corresponding to this particular paymentDto
        // the recordPaymentDto will be converted into a payment Dto which is then sent to
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(recordPaymentDto.getAmount());
        paymentDto.setEmiNumber(recordPaymentDto.getEmiNumber());
        paymentDto.setLoanRefId(recordPaymentDto.getLoanRefId());
        paymentDbService.recordPayment(paymentDto);
    }

    public void fetchBalance(BalanceDto balanceDto) {
        // have to fetch the loan entity/dto corresponding to this particular paymentDto,
        // need to store all the lumpSum payments somewhere
    }



}

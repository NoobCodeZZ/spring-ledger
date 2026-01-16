package com.noobcodezz.spring_ledger.service;

import com.noobcodezz.spring_ledger.db.service.LoanDbService;
import com.noobcodezz.spring_ledger.db.service.PaymentDbService;
import com.noobcodezz.spring_ledger.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public String fetchBalance(RequestBalanceDto requestBalanceDto) {
        // have to fetch the loan entity/dto corresponding to this particular paymentDto,
        // need to store all the lumpSum payments somewhere
        // the balance will take the loanRefId as input request inside the json
        // no need of creating balanceDto
        String loanRefID = requestBalanceDto.getLoanRefId();
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setEmiNumber(requestBalanceDto.getEmiNumber());
        balanceDto.setLoanRefId(loanRefID);

        // get all the lumpSum payment details from paymentService
        // get Loan Details from the loanDbService
        List<PaymentDto> payments = paymentDbService.returnPayments();
        LoanDto loanDto = loanDbService.findDtoByLoanReferenceID(requestBalanceDto.getLoanRefId()).orElseThrow(() -> new RuntimeException("Loan not found for reference ID: " + loanRefID));;


        double totalLumpSumPaid = payments.stream()
                .filter(payment -> loanRefID.equals(payment.getLoanRefId()))
                .mapToDouble(PaymentDto::getAmount)
                .sum();

        double totalRepayment = loanDto.getPrincipal()* (1+loanDto.getYears()*loanDto.getRoi()/100);
        double emiAmount = totalRepayment/(loanDto.getYears()*12);
        double emiAmountPaid = requestBalanceDto.getEmiNumber()*emiAmount;
        double balance = totalRepayment-totalLumpSumPaid - emiAmountPaid ;
        if(balance < 0) {
            balance = 0;
        }
        return String.format("Balance: %.2f", balance);
    }
}

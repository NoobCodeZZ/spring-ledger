package com.noobcodezz.spring_ledger.service;

import com.noobcodezz.spring_ledger.db.service.BankDbService;
import com.noobcodezz.spring_ledger.db.service.LoanDbService;
import com.noobcodezz.spring_ledger.db.service.PaymentDbService;
import com.noobcodezz.spring_ledger.db.service.UserDbService;
import com.noobcodezz.spring_ledger.exception.BankNotFoundException;
import com.noobcodezz.spring_ledger.exception.UserNotFoundException;
import com.noobcodezz.spring_ledger.exception.ValidationException;
import com.noobcodezz.spring_ledger.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanService {

    private final LoanDbService loanDbService;
    private final PaymentDbService paymentDbService;
    private final UserDbService userDbService;
    private final BankDbService bankDbService;

    public String createLoan(LoanCreationDto loanCreationDto) {

        List<String> errors = new ArrayList<>();

        if(bankDbService.findByBankReferenceId(loanCreationDto.getBankRefId()).isEmpty()) {
            errors.add("Bank with ID: " + loanCreationDto.getBankRefId() + " not found");
        }

        if(userDbService.findByUserReferenceId(loanCreationDto.getUserRefId()).isEmpty()) {
            errors.add("User with ID: " + loanCreationDto.getUserRefId() + " not found");
        }
        if(loanCreationDto.getRoi() <= 0 || loanCreationDto.getYears() <= 0 || loanCreationDto.getPrincipal() <= 0) {
            errors.add("Invalid parameters : input parameters cannot be negative or zero");
        }

        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }


        LoanDto loanDto = new LoanDto();
        loanDto.setPrincipal(loanCreationDto.getPrincipal());
        loanDto.setRoi(loanCreationDto.getRoi());
        loanDto.setYears(loanCreationDto.getYears());
        loanDto.setBankRefID(loanCreationDto.getBankRefId());
        loanDto.setUserRefID(loanCreationDto.getUserRefId());
        // the loan reference ID has been stored here, needs to be returned to the user
        String loanRefId = loanDbService.createLoan(loanDto);
        return loanRefId;
    }

    public void makePayment(RecordPaymentDto recordPaymentDto) {
        // have to fetch the loan entity/dto corresponding to this particular paymentDto
        // the recordPaymentDto will be converted into a payment Dto which is then sent to
        List<String> errors = new ArrayList<>();

        if(loanDbService.findByLoanReferenceID(recordPaymentDto.getLoanRefId()).isEmpty()) {
            errors.add("Loan with ID: " + recordPaymentDto.getLoanRefId()+ " not found");
        }

        if(recordPaymentDto.getAmount() <= 0|| recordPaymentDto.getEmiNumber() <= 0) {
            errors.add("Invalid parameters : input parameters cannot be negative or zero");
        }

        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }


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

        List<String> errors = new ArrayList<>();

        if(loanDbService.findByLoanReferenceID(requestBalanceDto.getLoanRefId()).isEmpty()) {
            errors.add("Loan with ID: " + requestBalanceDto.getLoanRefId()+ " not found");
        }

        if(requestBalanceDto.getEmiNumber() <= 0) {
            errors.add("Invalid parameters : input parameters cannot be negative or zero");
        }

        if(!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
        String loanRefID = requestBalanceDto.getLoanRefId();
        BalanceDto balanceDto = new BalanceDto();
        balanceDto.setEmiNumber(requestBalanceDto.getEmiNumber());
        balanceDto.setLoanRefId(loanRefID);

        // get all the lumpSum payment details from paymentService
        // get Loan Details from the loanDbService
        List<PaymentDto> payments = paymentDbService.returnPayments();
        LoanDto loanDto = loanDbService.findDtoByLoanReferenceID(requestBalanceDto.getLoanRefId()).orElseThrow(() -> new RuntimeException("Loan not found for reference ID: " + loanRefID));;

        // now have to filter this such that only that much lumpSum paid is calculated till that particular emi month
        double totalLumpSumPaid = payments.stream()
                .filter(payment -> loanRefID.equals(payment.getLoanRefId()))
                .mapToDouble(PaymentDto::getAmount)
                .sum();

        double totalRepayment = loanDto.getPrincipal()* (1+(double)loanDto.getYears()*(double)loanDto.getRoi()/100);
        double emiAmount = totalRepayment/((double)loanDto.getYears()*12);
        double emiAmountPaid = (double)requestBalanceDto.getEmiNumber()*emiAmount;
        double balance = totalRepayment-totalLumpSumPaid - emiAmountPaid ;
        if(balance < 0) {
            balance = 0;
        }
        return String.format("Balance: %.2f", balance);
    }
}

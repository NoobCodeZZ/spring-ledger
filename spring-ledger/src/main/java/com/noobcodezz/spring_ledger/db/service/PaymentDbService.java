package com.noobcodezz.spring_ledger.db.service;
import com.noobcodezz.spring_ledger.db.entity.PaymentEntity;
import com.noobcodezz.spring_ledger.db.repository.LoanRepository;
import com.noobcodezz.spring_ledger.db.repository.PaymentRepository;
import com.noobcodezz.spring_ledger.models.PaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentDbService {
    private final PaymentRepository paymentRepository;
    private final LoanRepository loanRepository;
    public void recordPayment(PaymentDto paymentDto) {
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setLoanId(loanRepository.findByReferenceId(paymentDto.getLoanRefId()).orElseThrow().getId());
        paymentEntity.setAmount(paymentDto.getAmount());
        paymentEntity.setEmiNumber(paymentDto.getEmiNumber());
        paymentRepository.save(paymentEntity);
    }
}

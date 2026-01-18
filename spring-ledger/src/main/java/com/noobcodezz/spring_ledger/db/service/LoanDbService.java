package com.noobcodezz.spring_ledger.db.service;

import com.noobcodezz.spring_ledger.db.entity.LoanEntity;
import com.noobcodezz.spring_ledger.db.repository.BankRepository;
import com.noobcodezz.spring_ledger.db.repository.LoanRepository;
import com.noobcodezz.spring_ledger.db.repository.UserRepository;
import com.noobcodezz.spring_ledger.models.LoanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoanDbService {
    private final LoanRepository loanRepository;
    private final BankRepository bankRepository;
    private final UserRepository userRepository;

//    public Optional<> findByUserID(String userID) {
//        loanRepository.findByUserId(userID);
//    }
//
//    private UserEntity mapDtoToEntity(UserEntity userEntity) {
//        BankEntity bankEntity = new BankEntity();
//    }

    public Optional<LoanEntity> findByLoanReferenceID(String loanRefID) {
        return loanRepository.findByReferenceId(loanRefID);
    }

    public List<LoanEntity> findByUserID(long userRefId) {
        return loanRepository.findByUserId(userRefId);
    }

    public List<LoanEntity> findByBankID(long bankRefId) {
        return loanRepository.findByBankId(bankRefId);
    }

    public String createLoan(LoanDto loanDto) {
        // Calculate interest: principal * roi * years / 100
        BigDecimal interest = loanDto.getPrincipal()
                .multiply(loanDto.getRoi())
                .multiply(BigDecimal.valueOf(loanDto.getYears()))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        // Calculate total repayment: interest + principal
        BigDecimal repaymentAmount = interest.add(loanDto.getPrincipal());

        // Calculate EMI months: years * 12
        int emiMonths = loanDto.getYears() * 12;

        // Calculate EMI amount: repaymentAmount / emiMonths
        BigDecimal emiAmount = repaymentAmount.divide(BigDecimal.valueOf(emiMonths), 2, RoundingMode.HALF_UP);

        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBankId(bankRepository.findByReferenceId(loanDto.getBankRefID()).orElseThrow().getId());
        loanEntity.setUserId(userRepository.findByReferenceId(loanDto.getUserRefID()).orElseThrow().getId());
        loanEntity.setRoi(loanDto.getRoi());
        loanEntity.setYears(loanDto.getYears());
        loanEntity.setPrincipal(loanDto.getPrincipal());
        loanEntity.setEmiDurationMonths(emiMonths);
        loanEntity.setEmiAmount(emiAmount);
        loanEntity.setTotalRepayment(repaymentAmount);
        loanRepository.save(loanEntity);
        return loanEntity.getReferenceId();
    }

    private LoanDto mapEntityToDto(LoanEntity loanEntity) {
        LoanDto loanDto = new LoanDto();
        loanDto.setPrincipal(loanEntity.getPrincipal());
        loanDto.setRoi(loanEntity.getRoi());
        loanDto.setYears(loanEntity.getYears());
        loanDto.setBankRefID(bankRepository.findById(loanEntity.getBankId())
                .map(bank -> bank.getReferenceId())
                .orElse(null));
        loanDto.setUserRefID(userRepository.findById(loanEntity.getUserId())
                .map(user -> user.getReferenceId())
                .orElse(null));
        loanDto.setEmiDurationMonths(loanEntity.getEmiDurationMonths());
        loanDto.setEmiAmount(loanEntity.getEmiAmount());
        loanDto.setTotalRepayment(loanEntity.getTotalRepayment());
        return loanDto;
    }

    public Optional<LoanDto> findDtoByLoanReferenceID(String loanRefID) {
        return loanRepository.findByReferenceId(loanRefID)
                .map(this::mapEntityToDto);
    }

//    private Optional<LoanEntity> mapDtoToEntity() {
//
//    }
//
//    private Optional<LoanDto> mapEntityToDto() {
//
//    }

}

package com.noobcodezz.spring_ledger.db.service;

import com.noobcodezz.spring_ledger.db.entity.LoanEntity;
import com.noobcodezz.spring_ledger.db.repository.BankRepository;
import com.noobcodezz.spring_ledger.db.repository.LoanRepository;
import com.noobcodezz.spring_ledger.db.repository.UserRepository;
import com.noobcodezz.spring_ledger.models.LoanDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    public void createLoan(LoanDto loanDto) {
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBankId(bankRepository.findByReferenceId(loanDto.getBankRefID()).orElseThrow().getId());
        loanEntity.setUserId(userRepository.findByReferenceId(loanDto.getUserRefID()).orElseThrow().getId());
        loanEntity.setRoi(loanDto.getRoi());
        loanEntity.setYears(loanDto.getYears());
        loanEntity.setPrincipal(loanDto.getPrincipal());
        loanRepository.save(loanEntity);
    }

    private LoanDto mapEntityToDto(LoanEntity loanEntity) {
        LoanDto loanDto = new LoanDto();
        loanDto.setPrincipal(loanEntity.getPrincipal());
        loanDto.setRoi(loanEntity.getRoi());
        loanDto.setYears((int) loanEntity.getYears());
        loanDto.setBankRefID(bankRepository.findById(loanEntity.getBankId())
                .map(bank -> bank.getReferenceId())
                .orElse(null));
        loanDto.setUserRefID(userRepository.findById(loanEntity.getUserId())
                .map(user -> user.getReferenceId())
                .orElse(null));
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

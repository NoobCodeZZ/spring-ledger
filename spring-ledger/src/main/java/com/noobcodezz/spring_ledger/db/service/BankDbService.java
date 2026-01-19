package com.noobcodezz.spring_ledger.db.service;

import com.noobcodezz.spring_ledger.db.entity.BankEntity;
import com.noobcodezz.spring_ledger.db.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BankDbService {
    private final BankRepository bankRepository;
    public Optional<BankEntity> findByBankReferenceId(String bankRefId) {
        return bankRepository.findByReferenceId(bankRefId);
    }
    
}

package com.noobcodezz.spring_ledger.db.repository;

import com.noobcodezz.spring_ledger.db.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long > {
    Optional<LoanEntity> findByReferenceId(String loanReferenceID);
    List<LoanEntity> findByUserId(long userReferenceId);
    List<LoanEntity> findByBankId(long bankReferenceId);
}

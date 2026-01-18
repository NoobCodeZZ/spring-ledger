package com.noobcodezz.spring_ledger.db.repository;

import com.noobcodezz.spring_ledger.db.entity.LoanEntity;
import com.noobcodezz.spring_ledger.db.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long > {
    List<PaymentEntity> findByLoanIdAndEmiNumberLessThanEqual(long loanId, int emiNumber);
}

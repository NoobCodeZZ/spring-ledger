package com.noobcodezz.spring_ledger.db.repository;

import com.noobcodezz.spring_ledger.db.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository  extends JpaRepository<BankEntity, Long> {
    Optional<BankEntity> findByReferenceId(String referenceId);
}

package com.noobcodezz.spring_ledger.db.repository;

import com.noobcodezz.spring_ledger.db.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByReferenceId(String referenceId);
}

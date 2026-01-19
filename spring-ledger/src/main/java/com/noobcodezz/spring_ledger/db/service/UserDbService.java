package com.noobcodezz.spring_ledger.db.service;

import com.noobcodezz.spring_ledger.db.entity.UserEntity;
import com.noobcodezz.spring_ledger.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDbService {
    private final UserRepository userRepository;
    public Optional<UserEntity> findByUserReferenceId(String userRefId) {
        return userRepository.findByReferenceId(userRefId);
    }
}

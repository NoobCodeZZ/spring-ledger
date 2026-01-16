package com.noobcodezz.spring_ledger.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BankDto {
    private String refId;
    private String bankName;
    private Instant createdAt;
}

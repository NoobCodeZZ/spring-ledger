package com.noobcodezz.spring_ledger.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private UUID loanID;
    private UUID UserRefID;
    private UUID BankRefID;
    private double principal;
    private double roi;
    private int years;
    private Instant createdAt;
}

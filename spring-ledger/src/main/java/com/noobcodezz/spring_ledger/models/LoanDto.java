package com.noobcodezz.spring_ledger.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private String loanID;
    private String UserRefID;
    private String BankRefID;
    private BigDecimal principal;
    private BigDecimal roi;
    private int years;
    private Instant createdAt;
}

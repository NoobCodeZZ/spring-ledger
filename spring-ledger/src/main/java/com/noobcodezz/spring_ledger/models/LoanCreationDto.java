package com.noobcodezz.spring_ledger.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoanCreationDto {
    private String bankRefId;
    private String userRefId;
    private BigDecimal principal;
    private int years;
    private BigDecimal roi;
}

package com.noobcodezz.spring_ledger.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoanCreationDto {
    private UUID bankRefId;
    private UUID userRefId;
    private double principal;
    private int years;
    private double roi;
}

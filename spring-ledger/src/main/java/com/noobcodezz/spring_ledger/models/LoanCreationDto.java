package com.noobcodezz.spring_ledger.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class LoanCreationDto {
    private String bankRefId;
    private String userRefId;
    private double principal;
    private int years;
    private double roi;
}

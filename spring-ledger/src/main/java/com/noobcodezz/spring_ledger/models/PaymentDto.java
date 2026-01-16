package com.noobcodezz.spring_ledger.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private String loanRefId;
    private double amount;
    private int emiNumber;
}

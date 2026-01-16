package com.noobcodezz.spring_ledger.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordPaymentDto {
    private String loanRefId;
    private double amount;
    private int emiNumber;
}

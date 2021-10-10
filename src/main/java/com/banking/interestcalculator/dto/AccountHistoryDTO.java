package com.banking.interestcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AccountHistoryDTO {

    private Integer accountId;
    private int bsb;
    private LocalDate creditedDate;
    private String trnType;
    private char creditDebitIndicator;
    private double amount;
}

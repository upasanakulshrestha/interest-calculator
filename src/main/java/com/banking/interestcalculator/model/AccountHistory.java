package com.banking.interestcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("account_history")
public class AccountHistory {

    @Id
    private int id;

    @Column("account_id")
    private Integer accountId;

    private int bsb;

    @Column("credited_date")
    private LocalDate creditedDate;

    @Column("trn_type")
    private String trnType;

    @Column("credit_debit_indicator")
    private char creditDebitIndicator;

    private double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public int getBsb() {
        return bsb;
    }

    public void setBsb(int bsb) {
        this.bsb = bsb;
    }

    public LocalDate getCreditedDate() {
        return creditedDate;
    }

    public void setCreditedDate(LocalDate creditedDate) {
        this.creditedDate = creditedDate;
    }

    public String getTrnType() {
        return trnType;
    }

    public void setTrnType(String trnType) {
        this.trnType = trnType;
    }

    public char getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    public void setCreditDebitIndicator(char creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

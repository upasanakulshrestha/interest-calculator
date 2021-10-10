package com.banking.interestcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("account_balance")
public class AccountBalance {

    @Id
    private Integer id;

    @Column("account_id")
    private Integer accountId;
    private double amount;
    private Integer bsb;

    public Integer getId() {
        return id;
    }
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}

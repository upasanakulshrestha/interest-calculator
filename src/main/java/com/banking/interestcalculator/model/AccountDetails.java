package com.banking.interestcalculator.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("account_details")
public class AccountDetails {

    @Id
    @ApiModelProperty(hidden=true)
    private Integer id;
    @Column("account_id")
    private Integer accountId;
    private int bsb;
    @Column("opening_date")
    private LocalDate openingDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", bsb=" + bsb +
                ", openingDate=" + openingDate.toString() +
                '}';
    }
}

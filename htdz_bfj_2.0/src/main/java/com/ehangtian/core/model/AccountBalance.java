package com.ehangtian.core.model;

import java.io.Serializable;

/**
 * Created by wangshuai on 2018/1/3.
 */
public class AccountBalance implements Serializable {

    private Long accountBalanceId;
    private String recordDate;
    private String businessType;
    private String accountNature;
    private String account;
    private Double validPrice;

    public Long getAccountBalanceId() {
        return accountBalanceId;
    }

    public void setAccountBalanceId(Long accountBalanceId) {
        this.accountBalanceId = accountBalanceId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Double getValidPrice() {
        return validPrice;
    }

    public void setValidPrice(Double validPrice) {
        this.validPrice = validPrice;
    }

    public String getAccountNature() {
        return accountNature;
    }

    public void setAccountNature(String accountNature) {
        this.accountNature = accountNature;
    }
}

package com.example.springsecuritybasic.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Column(name = "customer_id")
    private int customerId;

    @Id
    @Column(name="account_number")
    private long accountNumber;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "create_dt")
    private String createDt;

    public Account() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Account{" +
                "customerId=" + customerId +
                ", accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", branchAddress='" + branchAddress + '\'' +
                ", createDt='" + createDt + '\'' +
                '}';
    }
}

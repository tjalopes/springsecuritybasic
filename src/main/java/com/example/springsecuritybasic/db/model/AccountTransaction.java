package com.example.springsecuritybasic.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "account_transaction")
public class AccountTransaction {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name="account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private int transactionAmt;

    @Column(name = "closing_balance")
    private int closingBalance;

    @Column(name = "create_dt")
    private String createDt;

    public AccountTransaction() {
    }

    public String getTransactionId() {
        return transactionId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getTransactionDt() {
        return transactionDt;
    }

    public String getTransactionSummary() {
        return transactionSummary;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getTransactionAmt() {
        return transactionAmt;
    }

    public int getClosingBalance() {
        return closingBalance;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTransactionDt(Date transactionDt) {
        this.transactionDt = transactionDt;
    }

    public void setTransactionSummary(String transactionSummary) {
        this.transactionSummary = transactionSummary;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionAmt(int transactionAmt) {
        this.transactionAmt = transactionAmt;
    }

    public void setClosingBalance(int closingBalance) {
        this.closingBalance = closingBalance;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                "transactionId='" + transactionId + '\'' +
                ", accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", transactionDt=" + transactionDt +
                ", transactionSummary='" + transactionSummary + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", transactionAmt=" + transactionAmt +
                ", closingBalance=" + closingBalance +
                ", createDt='" + createDt + '\'' +
                '}';
    }
}

package com.example.springsecuritybasic.db.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name="loans")
public class Loans {

    @Id
    @GenericGenerator(
            name = "loansId",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "loans_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1"),
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loansId")
    @Column(name = "loan_number")
    private int loanNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="start_dt")
    private Date startDt;

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Column(name = "create_dt")
    private String createDt;

    public Loans() {
    }

    public int getLoanNumber() {
        return loanNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getStartDt() {
        return startDt;
    }

    public String getLoanType() {
        return loanType;
    }

    public int getTotalLoan() {
        return totalLoan;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public int getOutstandingAmount() {
        return outstandingAmount;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setStartDt(Date startDt) {
        this.startDt = startDt;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public void setTotalLoan(int totalLoan) {
        this.totalLoan = totalLoan;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setOutstandingAmount(int outstandingAmount) {
        this.outstandingAmount = outstandingAmount;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Loans{" +
                "loanNumber=" + loanNumber +
                ", customerId=" + customerId +
                ", startDt=" + startDt +
                ", loanType='" + loanType + '\'' +
                ", totalLoan=" + totalLoan +
                ", amountPaid=" + amountPaid +
                ", outstandingAmount=" + outstandingAmount +
                ", createDt='" + createDt + '\'' +
                '}';
    }
}

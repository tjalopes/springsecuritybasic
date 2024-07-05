package com.example.springsecuritybasic.db.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Table(name = "card")
public class Cards {

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
    @Column(name = "id")
    private int cardId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int availableAmount;

    @Column(name = "create_dt")
    private Date createDt;

    public Cards() {
    }

    public int getCardId() {
        return cardId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public int getTotalLimit() {
        return totalLimit;
    }

    public int getAmountUsed() {
        return amountUsed;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setTotalLimit(int totalLimit) {
        this.totalLimit = totalLimit;
    }

    public void setAmountUsed(int amountUsed) {
        this.amountUsed = amountUsed;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cardId=" + cardId +
                ", customerId=" + customerId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardType='" + cardType + '\'' +
                ", totalLimit=" + totalLimit +
                ", amountUsed=" + amountUsed +
                ", availableAmount=" + availableAmount +
                ", createDt=" + createDt +
                '}';
    }
}

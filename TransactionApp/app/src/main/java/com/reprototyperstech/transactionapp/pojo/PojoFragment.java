package com.reprototyperstech.transactionapp.pojo;

public class PojoFragment {

    String transactionDetails;
    String transactionStatus;
    String transactionAmount;
    String transactionDateTime;
    String narration;


    public PojoFragment(String transactionStatus, String transactionAmount, String transactionDateTime, String narration) {
        this.transactionStatus = transactionStatus;
        this.transactionAmount = transactionAmount;
        this.transactionDateTime = transactionDateTime;
        this.narration = narration;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }
}

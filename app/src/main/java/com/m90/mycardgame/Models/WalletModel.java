package com.m90.mycardgame.Models;

public class WalletModel {

    String amount;
    String date;

    public WalletModel(String amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

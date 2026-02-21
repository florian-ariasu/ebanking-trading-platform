package org.poo.cb;

public class Account {

    private final String currency;
    private double amount;

    public Account(String currency) {
        this.currency = currency;
        this.amount = 0.0;
    }

    public void addMoney(double value) {
        amount += value;
    }

    public void subtractMoney(double value) {
        amount -= value;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}

package org.poo.cb;

public interface CommissionStrategy {
    double applyCommission(double amount, double sourceBalance);
}

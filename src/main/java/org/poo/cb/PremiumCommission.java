package org.poo.cb;

public class PremiumCommission implements CommissionStrategy {

    @Override
    public double applyCommission(double amount, double sourceBalance) {
        return 0;
    }
}

package org.poo.cb;

public class StandardCommission implements CommissionStrategy {

    @Override
    public double applyCommission(double amount, double sourceBalance) {

        if (amount > sourceBalance / 2) {
            return amount * 0.01;
        }

        return 0;
    }
}

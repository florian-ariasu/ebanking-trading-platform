package org.poo.cb;

public class ExchangeMoneyCommand implements Command {
    private final BankSystem bankSystem;
    private final String email;
    private final String sourceCurrency;
    private final String destinationCurrency;
    private final double amount;

    public ExchangeMoneyCommand(BankSystem bankSystem,
                                String email,
                                String sourceCurrency,
                                String destinationCurrency,
                                double amount) {
        this.bankSystem = bankSystem;
        this.email = email;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.amount = amount;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(email);

        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }

        user.exchangeMoney(
                sourceCurrency,
                destinationCurrency,
                amount,
                bankSystem.getExchangeRates()
        );
    }
}

package org.poo.cb;

public class AddMoneyCommand implements Command {
    private final BankSystem bankSystem;
    private final String email;
    private final String currency;
    private final double amount;

    public AddMoneyCommand(BankSystem bankSystem,
                           String email,
                           String currency,
                           double amount) {
        this.bankSystem = bankSystem;
        this.email = email;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(email);

        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }

        Account account = user.getAccounts().get(currency);

        if (account == null) {
            System.out.println("Account in " + currency + " doesn't exist for " + email);
            return;
        }

        account.addMoney(amount);
    }
}

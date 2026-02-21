package org.poo.cb;

public class BuyPremiumCommand implements Command {
    private final BankSystem bankSystem;
    private final String email;

    public BuyPremiumCommand(BankSystem bankSystem, String email) {
        this.bankSystem = bankSystem;
        this.email = email;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(email);

        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }

        Account usdAccount = user.getAccounts().get("USD");

        if (usdAccount == null || usdAccount.getAmount() < 100) {
            System.out.println("Insufficient amount in account for buying premium option");
            return;
        }

        usdAccount.subtractMoney(100);
        user.setPremium(true);
    }
}

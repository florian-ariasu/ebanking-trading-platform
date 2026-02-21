package org.poo.cb;

public class AddAccountCommand implements Command {
    private final BankSystem bankSystem;
    private final String email;
    private final String currency;

    public AddAccountCommand(BankSystem bankSystem,
                             String email,
                             String currency) {
        this.bankSystem = bankSystem;
        this.email = email;
        this.currency = currency;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(email);

        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }

        if (user.getAccounts().containsKey(currency)) {
            System.out.println("Account in " + currency + " already exists for " + email);
            return;
        }

        user.getAccounts().put(currency, new Account(currency));
    }
}

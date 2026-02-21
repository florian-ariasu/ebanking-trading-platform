package org.poo.cb;

public class TransferMoneyCommand implements Command {

    private final BankSystem bankSystem;
    private final String email;
    private final String friendEmail;
    private final String currency;
    private final double amount;

    public TransferMoneyCommand(BankSystem bankSystem,
                                String email,
                                String friendEmail,
                                String currency,
                                double amount) {
        this.bankSystem = bankSystem;
        this.email = email;
        this.friendEmail = friendEmail;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(email);
        User friend = bankSystem.getUsers().get(friendEmail);

        if (user == null) {
            System.out.println("User with " + email + " doesn’t exist");
            return;
        }

        if (friend == null) {
            System.out.println("User with " + friendEmail + " doesn’t exist");
            return;
        }

        user.transferMoney(friend, currency, amount);
    }
}

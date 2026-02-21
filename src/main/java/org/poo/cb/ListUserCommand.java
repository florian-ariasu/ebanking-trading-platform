package org.poo.cb;

public class ListUserCommand implements Command {

    private final BankSystem bankSystem;
    private final String email;

    public ListUserCommand(BankSystem bankSystem, String email) {
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

        user.printUser();
    }
}


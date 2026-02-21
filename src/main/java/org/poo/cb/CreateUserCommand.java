package org.poo.cb;

public class CreateUserCommand implements Command {

    private final BankSystem bankSystem;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;

    public CreateUserCommand(BankSystem bankSystem,
                             String email,
                             String firstName,
                             String lastName,
                             String address) {
        this.bankSystem = bankSystem;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public void execute() {
        if (bankSystem.getUsers().containsKey(email)) {
            System.out.println("User with " + email + " already exists");
            return;
        }

        User user = new User(email, firstName, lastName, address);
        bankSystem.getUsers().put(email, user);
    }
}

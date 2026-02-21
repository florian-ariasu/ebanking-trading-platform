package org.poo.cb;

public class AddFriendCommand implements Command {
    private final BankSystem bankSystem;
    private final String userEmail;
    private final String friendEmail;

    public AddFriendCommand(BankSystem bankSystem, String userEmail, String friendEmail) {
        this.bankSystem = bankSystem;
        this.userEmail = userEmail;
        this.friendEmail = friendEmail;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(userEmail);
        User friend = bankSystem.getUsers().get(friendEmail);

        if (user == null) {
            System.out.println("User with " + userEmail + " doesn't exist");
            return;
        }

        if (friend == null) {
            System.out.println("User with " + friendEmail + " doesn't exist");
            return;
        }

        user.addFriend(friend);
    }
}


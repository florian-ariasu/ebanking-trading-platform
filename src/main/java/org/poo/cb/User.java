package org.poo.cb;
import java.util.*;

public class User {

    private final String email;
    private final String firstName;
    private final String lastName;
    private final String address;

    private final Set<String> friends = new LinkedHashSet<>();
    private final Map<String, Account> accounts = new LinkedHashMap<>();
    private final Map<String, Integer> stocks = new LinkedHashMap<>();

    private boolean premium = false;

    public User(String email, String firstName, String lastName, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void exchangeMoney(String sourceCurrency,
                              String destinationCurrency,
                              double amount,
                              Map<String, Map<String, Double>> exchangeRates) {

        Account source = accounts.get(sourceCurrency);
        Account destination = accounts.get(destinationCurrency);

        if (source == null || destination == null) {
            System.out.println("Account not found");
            return;
        }

        double rate = exchangeRates.get(destinationCurrency).get(sourceCurrency);

        double neededFromSource = amount * rate;

        CommissionStrategy strategy = premium ?
                new PremiumCommission() :
                new StandardCommission();

        double commission = strategy.applyCommission(
                neededFromSource,
                source.getAmount()
        );

        double totalWithdrawal = neededFromSource + commission;

        if (source.getAmount() < totalWithdrawal) {
            System.out.println("Insufficient amount in account "
                    + sourceCurrency + " for exchange");
            return;
        }

        source.subtractMoney(totalWithdrawal);
        destination.addMoney(amount);
    }

    public void printPortfolio() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"stocks\":[");

        boolean first = true;
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            if (!first) sb.append(",");
            sb.append("{\"stockName\":\"")
                    .append(entry.getKey())
                    .append("\",\"amount\":")
                    .append(entry.getValue())
                    .append("}");
            first = false;
        }

        sb.append("],\"accounts\":[");

        first = true;
        for (Account account : accounts.values()) {
            if (!first) sb.append(",");
            sb.append("{\"currencyName\":\"")
                    .append(account.getCurrency())
                    .append("\",\"amount\":\"")
                    .append(String.format("%.2f", account.getAmount()))
                    .append("\"}");
            first = false;
        }

        sb.append("]}");

        System.out.println(sb);
    }

    public void transferMoney(User friend,
                              String currency,
                              double amount) {

        if (!friends.contains(friend.getEmail())) {
            System.out.println("You are not allowed to transfer money to "
                    + friend.getEmail());
            return;
        }

        Account source = accounts.get(currency);

        if (source.getAmount() < amount) {
            System.out.println("Insufficient amount in account "
                    + currency + " for transfer");
            return;
        }

        Account destination = friend.getAccounts().get(currency);

        source.subtractMoney(amount);
        destination.addMoney(amount);
    }

    public void addFriend(User friend) {
        if (friends.contains(friend.getEmail())) {
            System.out.println("User with "
                    + friend.getEmail()
                    + " is already a friend");
            return;
        }

        friends.add(friend.getEmail());
        friend.getFriends().add(this.email);
    }

    public void printUser() {
        StringBuilder sb = new StringBuilder();

        sb.append("{\"email\":\"")
                .append(email)
                .append("\",\"firstname\":\"")
                .append(firstName)
                .append("\",\"lastname\":\"")
                .append(lastName)
                .append("\",\"address\":\"")
                .append(address)
                .append("\",\"friends\":[");

        boolean first = true;
        for (String friend : friends) {
            if (!first) sb.append(",");
            sb.append("\"").append(friend).append("\"");
            first = false;
        }

        sb.append("]}");

        System.out.println(sb);
    }
}

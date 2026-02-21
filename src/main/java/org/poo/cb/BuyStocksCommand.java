package org.poo.cb;

public class BuyStocksCommand implements Command {
    private final BankSystem bankSystem;
    private final String email;
    private final String stockName;
    private final int amount;

    public BuyStocksCommand(BankSystem bankSystem,
                           String email,
                           String stockName,
                           int amount) {
        this.bankSystem = bankSystem;
        this.email = email;
        this.stockName = stockName;
        this.amount = amount;
    }

    @Override
    public void execute() {
        User user = bankSystem.getUsers().get(email);
        if (user == null) {
            System.out.println("User with " + email + " doesn't exist");
            return;
        }

        double price = bankSystem.getStockValues().get(stockName).getLast(
        );

        double totalCost = price * amount;

        if (user.isPremium()) {
            if (bankSystem.getRecommendedStocks().contains(stockName)) {
                totalCost *= 0.95;
            }
        }

        Account usd = user.getAccounts().get("USD");

        if (usd.getAmount() < totalCost) {
            System.out.println("Insufficient amount in account for buying stock");
            return;
        }

        usd.subtractMoney(totalCost);

        user.getStocks().put(
                stockName,
                user.getStocks().getOrDefault(stockName, 0) + amount
        );
    }
}

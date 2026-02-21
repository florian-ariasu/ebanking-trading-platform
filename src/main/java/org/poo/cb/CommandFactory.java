package org.poo.cb;

public class CommandFactory {
    public static Command createCommand(String line,
                                        BankSystem bankSystem) {

        String[] tokens = line.split(" ");

        if (tokens[0].equals("CREATE") && tokens[1].equals("USER")) {

            String email = tokens[2];
            String firstName = tokens[3];
            String lastName = tokens[4];

            StringBuilder addressBuilder = new StringBuilder();
            for (int i = 5; i < tokens.length; i++) {
                addressBuilder.append(tokens[i]);
                if (i < tokens.length - 1) {
                    addressBuilder.append(" ");
                }
            }

            return new CreateUserCommand(
                    bankSystem,
                    email,
                    firstName,
                    lastName,
                    addressBuilder.toString()
            );
        }

        if (tokens[0].equals("LIST") && tokens[1].equals("USER")) {
            String email = tokens[2];
            return new ListUserCommand(bankSystem, email);
        }

        if (tokens[0].equals("ADD") && tokens[1].equals("FRIEND")) {
            String userEmail = tokens[2];
            String friendEmail = tokens[3];
            return new AddFriendCommand(bankSystem, userEmail, friendEmail);
        }

        if (tokens[0].equals("ADD") && tokens[1].equals("ACCOUNT")) {
            return new AddAccountCommand(
                    bankSystem,
                    tokens[2],
                    tokens[3]
            );
        }

        if (tokens[0].equals("ADD") && tokens[1].equals("MONEY")) {
            return new AddMoneyCommand(
                    bankSystem,
                    tokens[2],
                    tokens[3],
                    Double.parseDouble(tokens[4])
            );
        }

        if (tokens[0].equals("LIST") && tokens[1].equals("PORTFOLIO")) {
            return new ListPortfolioCommand(bankSystem, tokens[2]);
        }

        if (tokens[0].equals("EXCHANGE") && tokens[1].equals("MONEY")) {
            return new ExchangeMoneyCommand(
                    bankSystem,
                    tokens[2],
                    tokens[3],
                    tokens[4],
                    Double.parseDouble(tokens[5])
            );
        }

        if (tokens[0].equals("TRANSFER") && tokens[1].equals("MONEY")) {
            return new TransferMoneyCommand(
                    bankSystem,
                    tokens[2],
                    tokens[3],
                    tokens[4],
                    Double.parseDouble(tokens[5])
            );
        }

        if (tokens[0].equals("BUY") && tokens[1].equals("STOCKS")) {
            String email = tokens[2];
            String stockName = tokens[3];
            int amount = Integer.parseInt(tokens[4]);
            return new BuyStocksCommand(bankSystem, email, stockName, amount);
        }

        if (tokens[0].equals("RECOMMEND") && tokens[1].equals("STOCKS")) {
            return new RecommendStocksCommand(bankSystem);
        }

        if (tokens[0].equals("BUY") && tokens[1].equals("PREMIUM")) {
            return new BuyPremiumCommand(bankSystem, tokens[2]);
        }

        return null;
    }
}

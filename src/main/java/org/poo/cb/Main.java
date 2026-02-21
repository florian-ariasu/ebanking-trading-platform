package org.poo.cb;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Running Main");
            return;
        }

        String exchangeFile = args[0];
        String stockFile = args[1];
        String commandsFile = args[2];

        BankSystem bankSystem = BankSystem.getInstance();
        bankSystem.initialize(exchangeFile, stockFile);

        CommandProcessor processor =
                new CommandProcessor(bankSystem);

        processor.process(commandsFile);
    }
}

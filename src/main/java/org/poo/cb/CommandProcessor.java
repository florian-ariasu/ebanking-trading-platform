package org.poo.cb;

import java.io.*;
import java.util.*;

public class CommandProcessor {
    private final BankSystem bankSystem;

    public CommandProcessor(BankSystem bankSystem) {
        this.bankSystem = bankSystem;
    }

    public void process(String commandsFile) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream(commandsFile))))) {

            String line;

            while ((line = br.readLine()) != null) {

                Command command =
                        CommandFactory.createCommand(line, bankSystem);

                if (command != null) {
                    command.execute();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.poo.cb;

import java.util.*;

public class RecommendStocksCommand implements Command {
    private final BankSystem bankSystem;

    public RecommendStocksCommand(BankSystem bankSystem) {
        this.bankSystem = bankSystem;
    }

    @Override
    public void execute() {
        List<String> recommended = new ArrayList<>(bankSystem.getRecommendedStocks());

        Collections.sort(recommended);

        printResult(recommended);
    }

    private void printResult(List<String> stocks) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"stocksToBuy\":[");

        for (int i = 0; i < stocks.size(); i++) {
            sb.append("\"").append(stocks.get(i)).append("\"");
            if (i < stocks.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]}");

        System.out.println(sb);
    }
}

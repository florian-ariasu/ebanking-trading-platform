package org.poo.cb;
import java.util.*;
import java.io.*;

public class BankSystem {
    private static BankSystem instance;

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Map<String, Double>> exchangeRates = new HashMap<>();
    private final Map<String, List<Double>> stockValues = new HashMap<>();

    private BankSystem() {}

    public static BankSystem getInstance() {
        if (instance == null) {
            instance = new BankSystem();
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void initialize(String exchangeFile, String stockFile) {
        reset();

        loadExchangeRates(exchangeFile);
        loadStockValues(stockFile);
    }

    private void loadStockValues(String stockFile) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream(stockFile))))) {

            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String stockName = tokens[0];

                List<Double> values = new ArrayList<>();

                for (int i = 1; i < tokens.length; i++) {
                    values.add(Double.parseDouble(tokens[i]));
                }

                stockValues.put(stockName, values);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadExchangeRates(String exchangeFile) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        Objects.requireNonNull(getClass().getClassLoader()
                                .getResourceAsStream(exchangeFile))))) {

            String headerLine = br.readLine();
            if (headerLine == null) return;

            String[] headerCurrencies = headerLine.split(",");

            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String baseCurrency = tokens[0];

                Map<String, Double> rates = new HashMap<>();

                for (int i = 1; i < tokens.length; i++) {
                    String targetCurrency = headerCurrencies[i];
                    double rate = Double.parseDouble(tokens[i]);
                    rates.put(targetCurrency, rate);
                }

                exchangeRates.put(baseCurrency, rates);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<String> getRecommendedStocks() {
        Set<String> recommended = new HashSet<>();

        for (Map.Entry<String, List<Double>> entry : stockValues.entrySet()) {
            List<Double> values = entry.getValue();
            if (values.size() < 10) continue;

            double smaShort = calculateSMA(values, 5);
            double smaLong = calculateSMA(values, 10);

            if (smaShort > smaLong) {
                recommended.add(entry.getKey());
            }
        }

        return recommended;
    }

    private double calculateSMA(List<Double> values, int period) {
        int size = values.size();
        double sum = 0;

        for (int i = size - period; i < size; i++) {
            sum += values.get(i);
        }

        return sum / period;
    }

    public Map<String, Map<String, Double>> getExchangeRates() {
        return exchangeRates;
    }

    public Map<String, List<Double>> getStockValues() {
        return stockValues;
    }

    public void reset() {
        users.clear();
        exchangeRates.clear();
        stockValues.clear();
    }
}

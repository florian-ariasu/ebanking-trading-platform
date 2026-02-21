# eBanking Trading Platform — OOP Design Implementation

> [!IMPORTANT]
> This is the **third project assignment** for the Object-Oriented Programming (OOP) course (2nd year, 1st semester).
- This implementation was developed as a **personal re-implementation** for self-educational purposes after the official academic evaluation period.
- The project was developed entirely with **AI assistance throughout the development process**, focusing on understanding advanced Java design patterns and system architecture.

---

## Academic Transparency & AI Disclosure

This repository contains a personal study project based on a university assignment.

**Status:** This code was developed after the academic grading period concluded. It was not submitted for academic credit during the active course.

**AI Usage:** This implementation was developed with continuous AI assistance across all stages of development, including:

- Designing the overall system architecture
- Implementing every class and module
- Applying and refining design patterns
- Debugging failing test cases
- Optimizing business logic
- Refactoring for clean separation of responsibilities

**Learning Approach:** The educational value was derived from:

- Understanding how each design pattern was applied in context
- Analyzing the interactions between classes and components
- Iteratively debugging complex financial logic
- Studying how object-oriented principles scale within a medium-sized system

**Academic Integrity:** This repository serves strictly as a portfolio and learning artifact, demonstrating applied knowledge of OOP concepts, software architecture, and design patterns.

---

## Project Overview

This project simulates a simplified **eBanking and Trading System** that supports the following capabilities:

- User management
- Multi-currency accounts
- Currency exchange with configurable commission rules
- Stock purchasing
- SMA-based stock recommendations
- Premium subscription logic with associated financial benefits
- A command-driven architecture for structured input processing

The system reads commands from input files and produces structured JSON output.

---

## Design Patterns

### 1. Singleton Pattern — `BankSystem`

Ensures that only a single central banking system instance exists throughout the application lifecycle.

### 2. Command Pattern

Each user action is encapsulated into its own dedicated class implementing the following interface:

```java
public interface Command {
    void execute();
}
```

Command implementations include:

- `CreateUserCommand`
- `ExchangeMoneyCommand`
- `BuyStocksCommand`
- `RecommendStocksCommand`
- `BuyPremiumCommand`

### 3. Factory Pattern — `CommandFactory`

Responsible for interpreting input lines and instantiating the appropriate command object at runtime.

### 4. Strategy Pattern — Commission Logic

```java
public interface CommissionStrategy {
    double applyCommission(double amount, double sourceBalance);
}
```

Implementations:

- `StandardCommission` — Applies a 1% commission conditionally
- `PremiumCommission` — Applies 0% commission

This design enables dynamic, runtime-configurable commission behaviour.

---

## Financial Logic

### Currency Exchange

- Utilises an exchange rate matrix loaded from a CSV file
- Commission is applied using the Strategy pattern
- Premium users are exempt from commission charges

### Stock Recommendation Algorithm

Implements a simplified SMA (Simple Moving Average) crossover strategy:

1. Calculate the SMA over the last 5 trading days
2. Calculate the SMA over the last 10 trading days
3. If `SMA_5 > SMA_10`, the stock is marked as recommended

---

## Premium Subscription

**Command:** `BUY PREMIUM <email>`

**Rules:**

- One-time cost of 100 USD
- Requires sufficient account balance
- No output is produced on success
- Includes proper error handling for edge cases

**Benefits:**

- No commission on currency exchange
- 5% discount on recommended stock purchases

---

## Core Data Structures

| Structure | Purpose |
|---|---|
| `HashMap<String, User>` | User registry |
| `HashMap<String, Map<String, Double>>` | Multi-currency account balances |
| `HashMap<String, List<Double>>` | Historical stock price data |
| `HashSet<String>` | Premium user tracking |

---

## OOP Principles Applied

- **Encapsulation** — Internal state is protected and accessed through well-defined interfaces
- **Abstraction** — Complex logic is hidden behind clean, purpose-driven abstractions
- **Inheritance** — Shared behaviour is promoted through class hierarchies where appropriate
- **Polymorphism** — Runtime behaviour varies through interface implementations and method overriding

---

## Running the Project

This project uses **Gradle** as its build system.

**Linux / macOS:**

```bash
./gradlew clean test
```

**Windows:**

```bash
gradlew.bat clean test
```

---

## License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

---

*Built as a structured, AI-assisted learning project.*

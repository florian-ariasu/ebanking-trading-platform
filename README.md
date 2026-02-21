# eBanking Trading Platform -- OOP Design Implementation

> \[!IMPORTANT\] This is the **third project assignment** for the
> Object-Oriented Programming (OOP) course (2nd year, 1st semester). -
> This implementation was developed as a **personal re-implementation**
> for self-educational purposes after the official academic evaluation
> period. - The project was developed entirely with **AI assistance
> throughout the entire solving process**, focusing on understanding
> advanced Java design patterns and system architecture.

------------------------------------------------------------------------

## ⚠️ Academic Transparency & AI Disclosure

This repository contains a personal study project based on a university
assignment.

-   **Status:** This code was developed **after the academic grading
    period ended**. It was not submitted for academic credit during the
    active course.
-   **AI Usage:** This implementation was developed with **continuous AI
    assistance** across all stages of development:
    -   Designing the overall architecture
    -   Implementing every class
    -   Applying and refining design patterns
    -   Debugging failing test cases
    -   Optimizing business logic
    -   Refactoring for clean separation of responsibilities
-   **Learning Approach:** The educational value came from:
    -   Understanding how each design pattern was applied
    -   Analyzing the interaction between classes
    -   Iteratively debugging complex financial logic
    -   Studying how object-oriented principles scale in a medium-sized
        system
-   **Academic Integrity:** This repository serves strictly as a
    **portfolio and learning artifact**, demonstrating applied knowledge
    of OOP concepts, software architecture, and design patterns.

------------------------------------------------------------------------

# 🏦 Project Overview

This project simulates a simplified **eBanking and Trading System** that
supports:

-   User management
-   Multi-currency accounts
-   Currency exchange with commission rules
-   Stock purchasing
-   SMA-based stock recommendations
-   Premium subscription logic with financial benefits
-   Command-driven architecture

The system reads commands from input files and produces structured JSON
output.

------------------------------------------------------------------------

# 🧠 Design Patterns Used

## 1️⃣ Singleton Pattern -- `BankSystem`

Ensures only one central banking system instance exists.

## 2️⃣ Command Pattern

Each action is encapsulated into its own class implementing:

``` java
public interface Command {
    void execute();
}
```

Examples: - CreateUserCommand - ExchangeMoneyCommand -
BuyStocksCommand - RecommendStocksCommand - BuyPremiumCommand

## 3️⃣ Factory Pattern -- `CommandFactory`

Responsible for interpreting input lines and instantiating the correct
command.

## 4️⃣ Strategy Pattern -- Commission Logic

``` java
public interface CommissionStrategy {
    double applyCommission(double amount, double sourceBalance);
}
```

Implementations: - StandardCommission (1% conditionally) -
PremiumCommission (0%)

Allows dynamic runtime commission behavior.

------------------------------------------------------------------------

# 💰 Financial Logic

## Currency Exchange

-   Uses exchange matrix loaded from CSV.
-   Commission applied using Strategy pattern.
-   Premium users pay no commission.

## Stock Recommendation Algorithm

Implements simplified SMA crossover:

1.  Calculate SMA over last 5 days
2.  Calculate SMA over last 10 days
3.  If SMA_5 \> SMA_10 → stock recommended

------------------------------------------------------------------------

# ⭐ Premium Option (Bonus Feature)

## BUY PREMIUM `<email>`{=html}

Rules: - Costs 100 USD (one-time) - Requires sufficient balance - No
output on success - Proper error handling

Benefits: - No exchange commission - 5% discount on recommended stocks

------------------------------------------------------------------------

# 📂 Core Data Structures

-   HashMap\<String, User\>
-   HashMap\<String, Map\<String, Double\>\>
-   HashMap\<String, List`<Double>`{=html}\>
-   HashSet`<String>`{=html}

------------------------------------------------------------------------

# 🧩 OOP Principles Applied

-   Encapsulation
-   Abstraction
-   Inheritance
-   Polymorphism

------------------------------------------------------------------------

# 🚀 Running the Project

This project uses Gradle.

Run tests:

``` bash
./gradlew clean test
```

Windows:

``` bash
gradlew.bat clean test
```

------------------------------------------------------------------------

# 📌 Recommended GitHub Repository Name

ebanking-trading-system-oop

Alternative options: - oop-ebanking-platform -
financial-system-oop-design - ebanking-sma-trading-platform

------------------------------------------------------------------------

# 📜 License

MIT License.

------------------------------------------------------------------------

⭐ Built as a structured AI-assisted learning project.

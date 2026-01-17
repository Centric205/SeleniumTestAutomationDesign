# Selenium Java BDD Test Automation Framework

## 📌 Overview

This project is a **senior-level Selenium Test Automation Framework** built using **Java**, **Selenium WebDriver**, **TestNG**, and **Cucumber (BDD)**.

The framework is designed with **scalability, maintainability, and parallel execution** in mind and follows industry best practices, clean architecture principles, and well-known design patterns.

It supports:
- UI test automation using Selenium
- Behavior Driven Development (BDD) with Cucumber
- Parallel execution using ThreadLocal WebDriver
- Externalized configuration
- Logging, reporting, and CI/CD readiness

---

## 🎯 Goals of the Framework

- Separate **test intent** from **test implementation**
- Avoid code duplication and flaky tests
- Support parallel execution safely
- Be easy to extend for new browsers, environments, and test types
- Be suitable for **enterprise-scale test suites**

---

## 🧱 Architecture Overview

| Layer                   | Description                  |
|--------------------------|------------------------------|
| **Feature Files**        | Gherkin / Business language  |
| **Step Definitions**     | Glue code                    |
| **Page Objects / Flows** | UI behavior                  |
| **Driver / Utils / Config** | Framework core            |


👉 **Cucumber is intentionally kept as a thin layer**  
👉 Core framework logic is independent of BDD

---

## 📁 Project Structure

src
├── main
│   └── java
│       └── framework
│           ├── base              # BasePage and shared abstractions
│           ├── config            # Configuration reader
│           ├── driver            # WebDriver factory and lifecycle
│           ├── pages             # Page Object Model classes
│           └── utils             # Waits, screenshots, helpers
│
├── test
│   ├── java
│   │   ├── runners              # Cucumber TestNG runners
│   │   ├── stepdefinitions      # Step definition classes
│   │   └── hooks                # Cucumber hooks (setup/teardown)
│   │
│   └── resources
│       ├── features             # Cucumber feature files
│       └── config.properties
│
└── pom.xml


---

## 🧠 Design Patterns Used

### ✅ Page Object Model (POM)
- Encapsulates UI behavior
- Improves readability and maintainability
- Reduces duplication

### ✅ Factory Pattern
- Centralized WebDriver creation
- Easy browser extensibility

### ✅ Singleton Pattern
- Configuration management
- Shared framework components

### ✅ ThreadLocal Pattern
- Enables safe parallel execution
- Each thread gets its own WebDriver instance

---

## ⚙️ Configuration Management

All environment-specific values are externalized in `config.properties`.

Example:
#### Configuration Settings

- **browser**: `chrome`
- **baseUrl**: `https://example.com`
- **headless**: `false`
- **explicitWait**: `10`


This allows:
- Environment switching without code changes
- CI/CD friendly execution

---

## 🌐 Browser Support

- Chrome
- Firefox

(Browsers are managed using **WebDriverManager** — no manual driver setup required)

---

## 🚀 Parallel Execution

Parallel execution is achieved using:
- TestNG parallel configuration
- ThreadLocal WebDriver storage

This allows:
- Faster test execution
- Stable parallel runs without session clashes

---

## 📊 Reporting

The framework integrates **Extent Reports** to provide:
- Detailed execution reports
- Step-level visibility
- Screenshots on failure
- Parallel execution support

Reports are generated automatically after test execution.

---

## 📝 Logging

Logging is handled using **Log4j2** and includes:
- Test lifecycle events
- Page interactions
- Debug-level troubleshooting logs

Logs help diagnose failures, especially in CI environments.

---

## 🔁 Retry Mechanism

A configurable retry analyzer is implemented to:
- Retry failed tests (for transient issues only)
- Reduce noise from flaky UI failures

Retries are controlled and not abused to mask real defects.

---

## ▶️ How to Run Tests

### Run all tests
```bash
mvn clean test

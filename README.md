# Parallel Selenium Test Automation Framework

## 📌 Project Overview

This project is a **Java-based Selenium Test Automation Framework** designed to execute tests in **parallel across multiple browsers** using **TestNG**. It follows best practices like the **Page Object Model (POM)** and ensures **thread-safe WebDriver management** for scalable and efficient test execution.

The framework demonstrates automated testing of a **user registration flow** (nopCommerce_4.80.9 application running locally) with support for:

* Parallel test execution
* Cross-browser testing (Chrome, Firefox, Edge)
* Thread-safe driver handling
* Screenshot capture on failure
* Data-driven testing using TestNG DataProviders

---

## 🧱 Project Structure

```
com.amalw.parallel
│
├── base
│   └── BaseTest.java          # Test setup & teardown logic
│
├── driver
│   └── DriverFactory.java    # Thread-safe WebDriver management
│
├── pages
│   ├── BasePage.java         # Common reusable Selenium actions
│   └── RegisterPage.java     # Page Object for registration page
│
├── tests
│   └── RegistrationTest.java # Test class with parallel data execution
│
├── utils
│   └── ScreenshotUtil.java   # Screenshot capture utility
│
└── testng.xml                # TestNG suite configuration for parallel runs
```

---

## 🚀 Key Features

### ✅ Thread-Safe WebDriver Management

* Uses `ThreadLocal<WebDriver>` to ensure each test thread gets its own browser instance.
* Prevents conflicts during parallel execution.

### ✅ Parallel Execution

* Tests run in parallel at:

  * **Test level** (multiple browsers)
  * **DataProvider level** (multiple datasets)
* Configurable via `testng.xml`

### ✅ Cross-Browser Testing

Supports:

* Chrome
* Firefox
* Edge

### ✅ Page Object Model (POM)

* Clean separation between test logic and UI interactions.
* Improves maintainability and readability.

### ✅ Explicit Wait Strategy

* Uses `WebDriverWait` for stable element interactions.
* Implicit wait is disabled to avoid conflicts.

### ✅ Screenshot Capture on Failure

* Automatically captures screenshots for failed tests.
* Saved under:

  ```
  target/screenshots/
  ```

---

## 📦 Dependencies

Make sure your project includes the following dependencies:

### Maven Dependencies (Recommended)

```xml
<dependencies>

    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.x.x</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.x.x</version>
        <scope>test</scope>
    </dependency>

    <!-- WebDriverManager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.x.x</version>
    </dependency>

</dependencies>
```

---

## ⚙️ Setup Instructions

### 1. Prerequisites

* Java JDK 8 or higher
* Maven (or Gradle)
* IDE (IntelliJ IDEA / Eclipse)
* Browsers installed:

  * Chrome
  * Firefox
  * Edge

---

### 2. Clone the Repository

```bash
git clone <your-repo-url>
cd <project-folder>
```

---

### 3. Start the Application Under Test

Download nopCommerce_4.80.9 and run Nop.Web.exe
Ensure your application is running at:

```
http://localhost:5000/register
```

> ⚠️ Update the URL in `RegisterPage.java` if needed:

```java
private final String REG_URL = "http://localhost:5000/register";
```

---

### 4. Run Tests

#### Option A: Using Maven

```bash
mvn clean test
```

#### Option B: Using TestNG XML

Run the `testng.xml` file directly from your IDE.

---

## 🧪 Test Execution Details

### Parallel Configuration (`testng.xml`)

```xml
<suite name="ParallelRegistrationSuite" parallel="tests" thread-count="12">
```

* Runs tests across browsers in parallel
* Each `<test>` block represents a browser

### Data-Driven Execution

* Uses `@DataProvider(parallel = true)`
* Multiple datasets executed simultaneously

---

## 🔍 How It Works

### Driver Lifecycle

* `DriverFactory.initDriver(browser)` → initializes driver per thread
* `DriverFactory.getDriver()` → retrieves thread-specific driver
* `DriverFactory.quitDriver()` → cleans up after test

### Test Flow

1. Browser initialized (BaseTest)
2. Registration page opened
3. Form filled with random email (UUID)
4. Form submitted
5. Assertions validate success
6. Screenshot captured on failure
7. Browser closed

---

## 📸 Screenshot Example

Screenshots are saved with timestamp:

```
target/screenshots/testRegistration_171234567890.png
```

---

## ⚠️ Notes & Best Practices

* **Unique Emails**: Generated using `UUID` to avoid duplication.
* **No Implicit Waits**: Prevents flaky tests when combined with explicit waits.
* **Thread Safety**: Always access driver via `DriverFactory`.
* **Timeouts**:

  * Page load: 30 seconds
  * Explicit wait: 15 seconds

---

## 👨‍💻 Author

Developed as a scalable Selenium automation framework for learning and real-world testing scenarios.
By Amal W

---

## 📄 License

This project is open-source and available for educational and professional use.

---

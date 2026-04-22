package com.amalw.parallel.driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/*DriverFactory creates and manages thread-safe WebDriver instances */
public final class DriverFactory {

	// ThreadLocal ensures each thread has its own WebDriver instance
	private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	// Private constructor to prevent instantiation
	private DriverFactory() {
	}

	// Returns the WebDriver instance associated with the current thread
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	// Initializes WebDriver based on the specified browser type
	public static void initDriver(String browser) {

		if (browser == null) {
			throw new IllegalArgumentException("Browser name is null");
		}

		WebDriver driver;
		// Create driver based on browser type
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOpts = new ChromeOptions();
			chromeOpts.addArguments("--start-maximized");
			driver = new ChromeDriver(chromeOpts);
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.setAcceptInsecureCerts(true);
			driver = new FirefoxDriver(ffOptions);
			break;

		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
		// Set implicit wait to zero
		driver.manage().timeouts().implicitlyWait(Duration.ZERO);
		// Set page load timeout for stability
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

		// Set into ThreadLocal
		tlDriver.set(driver);
	}

	// Quits the driver and removes it from ThreadLocal storage
	public static void quitDriver() {
		// get current thread driver
		WebDriver driver = tlDriver.get();
		if (driver != null) {
			driver.quit();
			tlDriver.remove();
		}
	}
}
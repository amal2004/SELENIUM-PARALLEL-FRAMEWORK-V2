package com.amalw.parallel.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.amalw.parallel.driver.DriverFactory;
import com.amalw.parallel.utils.ScreenshotUtil;



/*Foundation class for all test classes and it handles WebDriver initialization and cleanup, and provides
common setup/teardown logic for test execution lifecycle.*/

public class BaseTest {

	// Runs before each test method; initializes WebDriver
	    @Parameters("browser")
	    @BeforeMethod(alwaysRun = true)
	    public void setUp(@Optional("chrome") String browser) {
	        DriverFactory.initDriver(browser);
	    }

	 // Runs after each test method; handles cleanup and failure actions
	    @AfterMethod(alwaysRun = true)
	    public void tearDown(ITestResult result) {
	    	
	        // Take screenshot if test fails
	        if (result.getStatus() == ITestResult.FAILURE) {
	            ScreenshotUtil.takeScreenshot(result.getName());
	        }
	     // close browser session and cleanup thread
	        DriverFactory.quitDriver();
	    }

	    //Returns the current thread-safe WebDriver instance
	    public WebDriver getDriver() {
	    	 // access driver from factory
	        return DriverFactory.getDriver();
	    }
}

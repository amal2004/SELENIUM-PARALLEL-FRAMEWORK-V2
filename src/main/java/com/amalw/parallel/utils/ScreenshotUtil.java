package com.amalw.parallel.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.amalw.parallel.driver.DriverFactory;


public class ScreenshotUtil {

    // base folder for screenshots
    private static final String SCREENSHOT_DIR = "target/screenshots/";

    /**
     * Takes screenshot and returns the path (relative or absolute) to the file.
     */
    public static String takeScreenshot(String screenshotName) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) {
            System.err.println("Driver is null. Cannot take screenshot.");
            return null;
        }
        Path destDir = Paths.get(SCREENSHOT_DIR);
        try {
            Files.createDirectories(destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = screenshotName + "_" + System.currentTimeMillis() + ".png";
        Path destPath = destDir.resolve(filename);
        File destFile = destPath.toFile();

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            Files.copy(src.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destPath.toString();
    }
}
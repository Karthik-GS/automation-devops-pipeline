package com.config.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            // Robust check: Look for Maven property OR if running on Jenkins container
            String headlessProp = System.getProperty("headless");
            boolean isJenkins = System.getenv("JENKINS_URL") != null;

            if ("true".equalsIgnoreCase(headlessProp) || isJenkins) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                System.out.println(">>> CI/CD Environment Detected. Launching Chrome HEADLESS.");
            } else {
                System.out.println(">>> Local Environment Detected. Launching Chrome HEADED.");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset to null so next tests start fresh
        }
    }
}
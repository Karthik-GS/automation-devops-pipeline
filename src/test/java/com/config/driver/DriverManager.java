package com.config.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();

            String headlessProp = System.getProperty("headless");
            boolean isJenkins = System.getenv("JENKINS_URL") != null;

            if ("true".equalsIgnoreCase(headlessProp) || isJenkins) {
                // This tells WebDriverManager to handle the architecture discovery cleanly
                WebDriverManager.chromedriver().setup();

                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");

                // Crucial flag for bare-bones Docker environments lacking standard system shared memory
                options.addArguments("--disable-headless-sharing");

                System.out.println(">>> CI/CD Environment: Browser managed natively by WebDriverManager.");
            } else {
                WebDriverManager.chromedriver().setup();
                System.out.println(">>> Local Environment: Launching standard Chrome.");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
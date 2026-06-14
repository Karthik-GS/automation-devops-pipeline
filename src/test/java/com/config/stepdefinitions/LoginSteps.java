package com.config.stepdefinitions;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginSteps {
    WebDriver driver;

    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Essential configuration for DevOps / CI pipelines
        if (Boolean.parseBoolean(System.getProperty("headless"))) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("User enters valid username {string} and password {string}")
    public void user_enters_valid_username_and_password(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        driver.findElement(By.cssSelector("button.radius")).click();
    }

    @Then("User should be redirected to the secure dashboard page")
    public void user_should_be_redirected_to_the_secure_dashboard_page() {
        boolean isDashboardDisplayed = driver.findElement(By.id("flash")).isDisplayed();
        Assert.assertTrue("Login verification failed!", isDashboardDisplayed);
        driver.quit();
    }
}

package com.config.stepdefinitions;



import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.config.pages.LoginPage;
import com.config.utils.DatabaseUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CombinedSteps {
    WebDriver driver;
    Response apiResponse;

    @When("User queries the database for user details")
    public void queryDB() throws Exception {
        String username = DatabaseUtil.getDbData("SELECT username FROM users LIMIT 1", "username");
        System.out.println("Fetched from database: " + username);
    }

    @When("User sends a GET request to the user API endpoint")
    public void callAPI() {
        apiResponse = RestAssured.get("https://reqres.in/api/users/2");
        Assert.assertEquals(200, apiResponse.getStatusCode());
    }

    @Then("The API response status should be confirmed successfully")
    public void verifyAPI() {
        String email = apiResponse.jsonPath().getString("data.email");
        System.out.println("Validated API Email asset: " + email);
    }
}
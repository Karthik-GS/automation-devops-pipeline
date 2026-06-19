package com.config.stepdefinitions;

import com.config.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        // Initializes the browser cleanly before the scenario starts
        DriverManager.getDriver();
    }

    @After
    public void tearDown() {
        // Guarantees the browser closes, even if a test step fails
        DriverManager.quitDriver();
    }
}

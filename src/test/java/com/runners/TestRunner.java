package com.runners;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/features",
//        glue = {"com.karthik.config.stepdefinitions"},
//        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"} // Generates Allure lifecycle json log
//)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.config.stepdefinitions"},
        plugin = {"pretty", "html:target/cucumber-reports.html", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        monochrome = true
)
public class TestRunner {
}
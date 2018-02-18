package com.epam.tech_mentoring.patterns.tests.hooks;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class ScenarioHook {
    protected WebDriverManager driverManager = new WebDriverManager(System.getProperty("browserName", "chrome"));

    @BeforeMethod(alwaysRun = true)
    public void beforeScenario() {
        driverManager.deleteAllCookies();
    }
}

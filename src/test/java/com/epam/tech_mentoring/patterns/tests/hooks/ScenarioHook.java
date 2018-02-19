package com.epam.tech_mentoring.patterns.tests.hooks;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class ScenarioHook {
    @BeforeMethod(alwaysRun = true)
    public void beforeScenario() {
        WebDriverManager.driver().manage().deleteAllCookies();
    }
}

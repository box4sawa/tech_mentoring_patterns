package com.epam.tech_mentoring.patterns.tests.hooks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class FeatureHook extends ScenarioHook {
    @BeforeClass(alwaysRun = true)
    public void beforeFeature() {
        driverManager.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterFeature() {
        driverManager.quite();
    }
}

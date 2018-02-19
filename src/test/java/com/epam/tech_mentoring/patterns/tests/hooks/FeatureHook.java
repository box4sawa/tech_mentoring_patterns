package com.epam.tech_mentoring.patterns.tests.hooks;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class FeatureHook extends ScenarioHook {
    @BeforeClass(alwaysRun = true)
    public void beforeFeature() { WebDriverManager.driver(); }

    @AfterClass(alwaysRun = true)
    public void afterFeature() {
        WebDriverManager.quite();
    }
}

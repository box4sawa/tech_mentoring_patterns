package com.epam.tech_mentoring.patterns.dsl.steps;

import com.epam.tech_mentoring.patterns.dsl.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class InitialSteps {
    private WebDriver driver;

    public InitialSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        new HomePage(driver).get();
    }
}

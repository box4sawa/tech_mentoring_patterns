package com.epam.tech_mentoring.patterns.core.driver_factory;

import com.epam.tech_mentoring.patterns.core.driver_factory.desktop.ChromeDriverFactory;
import com.epam.tech_mentoring.patterns.core.driver_factory.desktop.FireFoxDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private WebDriver driver;
    private WebDriverFactory factory;

    public WebDriverManager(String browserName) {
        if (browserName.equalsIgnoreCase("chrome"))
            factory = new ChromeDriverFactory();
        else
            factory = new FireFoxDriverFactory();
    }

    public WebDriver getDriver() {
        if (driver == null)
            driver = factory.createDriver();
        return driver;
    }

    public void deleteAllCookies() {
        if (driver != null)
            driver.manage().deleteAllCookies();
    }

    public void quite() {
        if (driver != null)
            driver.quit();
    }
}

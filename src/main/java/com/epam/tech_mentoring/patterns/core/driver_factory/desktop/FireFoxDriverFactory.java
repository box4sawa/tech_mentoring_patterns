package com.epam.tech_mentoring.patterns.core.driver_factory.desktop;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FireFoxDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver createDriverWithCapabilities(DesiredCapabilities capabilities) {
        WebDriver driver =  new FirefoxDriver(new FirefoxOptions(capabilities));
        driver.manage().window().maximize();
        return driver;
    }
}

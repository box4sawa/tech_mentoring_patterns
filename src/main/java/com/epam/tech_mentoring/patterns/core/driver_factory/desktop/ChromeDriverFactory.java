package com.epam.tech_mentoring.patterns.core.driver_factory.desktop;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverFactory extends WebDriverFactory {
    @Override
    public WebDriver createDriverWithCapabilities(DesiredCapabilities capabilities) {
        WebDriverManager.chromedriver().version("2.33").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.merge(capabilities);
        return new ChromeDriver(options);
    }
}

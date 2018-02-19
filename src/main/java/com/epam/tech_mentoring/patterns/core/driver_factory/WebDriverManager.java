package com.epam.tech_mentoring.patterns.core.driver_factory;

import com.epam.tech_mentoring.patterns.core.driver_factory.desktop.ChromeDriverFactory;
import com.epam.tech_mentoring.patterns.core.driver_factory.desktop.FireFoxDriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverManager {
    private static WebDriver driver;
    private static WebDriverFactory factory;

    static {
        String browserName = System.getProperty("browserName", "chrome");

        if (browserName.equalsIgnoreCase("chrome"))
            factory = new ChromeDriverFactory();
        else
            factory = new FireFoxDriverFactory();
    }

    public static WebDriver driver() {
        if (driver == null)
            driver = factory.createDriver();
        return driver;
    }

    public static void deleteAllCookies() {
        if (driver != null)
            driver.manage().deleteAllCookies();
    }

    public static void quite() {
        if (driver != null)
            driver.quit();
    }
}

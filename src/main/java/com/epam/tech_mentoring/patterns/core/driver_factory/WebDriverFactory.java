package com.epam.tech_mentoring.patterns.core.driver_factory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class WebDriverFactory {
    public abstract WebDriver createDriverWithCapabilities(DesiredCapabilities capabilities);

    public WebDriver createDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability("javascriptEnabled", true);
        return createDriverWithCapabilities(capabilities);
    }
}

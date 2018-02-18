package com.epam.tech_mentoring.patterns.dsl.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public static final String BASE_URL = "https://pn.com.ua/";

    @Override
    public boolean isLoaded() {
        return driver.getTitle().contains("(Прайс Навигатор): товары и услуги, магазины в");
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage get() {
        driver.get(BASE_URL);
        return this;
    }

    public void clickOnLinkByText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }
}

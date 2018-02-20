package com.epam.tech_mentoring.patterns.dsl.pages;

import com.epam.tech_mentoring.patterns.core.page_factory.elements.Link;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    public static final String BASE_URL = "https://pn.com.ua/";

    @FindBy(css = "li a")
    public List<Link> galleryLinks;

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
        galleryLinks.stream()
                .filter(link -> link.getText().equalsIgnoreCase(linkText))
                .findFirst()
                .ifPresent(Link::click);
    }
}

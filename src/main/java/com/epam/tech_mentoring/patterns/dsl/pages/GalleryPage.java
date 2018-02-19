package com.epam.tech_mentoring.patterns.dsl.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GalleryPage extends BasePage {

    @FindBy(css = "#producers-filter-block .popular a")
    public List<WebElement> brands;

    @FindBy(css = ".item .catalog-block-head")
    public List<WebElement> itemTitles;

    @FindBy(css = ".item .price span strong")
    public List<WebElement> itemPrices;

    @FindBy(css = "#price-max")
    public WebElement maxPriceInput;

    @FindBy(css = "#pricebutton")
    public WebElement applyPriceButton;



    @Override
    public boolean isLoaded() {
        return driver.getTitle().contains("Сравнить цены в Прайс Навигатор. Купить");
    }

    public GalleryPage(WebDriver driver) {
        super(driver);
    }

    public void filterByBrandName(String brandName) {
        brands.stream()
                .filter(element -> element.getText().equals(brandName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void typeMaxPriceRange(String price) {
        new Actions(driver).doubleClick(maxPriceInput).sendKeys(price).perform();
        maxPriceInput.sendKeys("\t");
        applyPriceButton.click();
    }
}

package com.epam.tech_mentoring.patterns.dsl.pages;

import com.epam.tech_mentoring.patterns.core.page_factory.elements.Button;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Input;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Link;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Text;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GalleryPage extends BasePage {

    @FindBy(css = "#producers-filter-block .popular a")
    public List<Link> brands;

    @FindBy(css = ".item .catalog-block-head")
    public List<Link> itemTitles;

    @FindBy(css = ".item .price span strong")
    public List<Text> itemPrices;

    @FindBy(css = "#price-max")
    public Input maxPriceInput;

    @FindBy(css = "#pricebutton")
    public Button applyPriceButton;



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
                .ifPresent(Link::click);
    }

    public void typeMaxPriceRange(String price) {
        new Actions(driver).doubleClick(maxPriceInput.getElement()).sendKeys(price).perform();
        maxPriceInput.removeFocus();
        applyPriceButton.click();
    }
}

package com.epam.tech_mentoring.patterns.core.page_factory.elements;

import org.openqa.selenium.WebElement;

public abstract class CustomWebElement {
    protected WebElement element;

    public CustomWebElement(WebElement element) {
        this.element = element;
    }

    public WebElement getElement() {
        return element;
    }
}

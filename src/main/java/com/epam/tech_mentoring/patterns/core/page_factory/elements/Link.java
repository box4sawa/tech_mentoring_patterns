package com.epam.tech_mentoring.patterns.core.page_factory.elements;

import org.openqa.selenium.WebElement;

public class Link extends CustomWebElement {
    public Link(WebElement element) {
        super(element);
    }

    public String getText() {
        return element.getText();
    }

    public void click() {
        element.click();
    }
}

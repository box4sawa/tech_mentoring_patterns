package com.epam.tech_mentoring.patterns.core.page_factory.elements;

import org.openqa.selenium.WebElement;

public class Button extends CustomWebElement {
    public Button(WebElement element) {
        super(element);
    }

    public void click() { element.click(); }
}

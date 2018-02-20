package com.epam.tech_mentoring.patterns.core.page_factory.elements;

import org.openqa.selenium.WebElement;

public class Text extends CustomWebElement {
    public Text(WebElement element) {
        super(element);
    }

    public String getText() {
        return element.getText();
    }
}

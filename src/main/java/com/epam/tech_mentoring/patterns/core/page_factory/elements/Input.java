package com.epam.tech_mentoring.patterns.core.page_factory.elements;

import org.openqa.selenium.WebElement;

public class Input extends CustomWebElement {
    public Input(WebElement element) {
        super(element);
    }

    public void clear() {
        element.clear();
    }

    public void sendText(String text) {
        element.sendKeys(text);
    }

    public void removeFocus() {
        sendText("\t");
    }
}

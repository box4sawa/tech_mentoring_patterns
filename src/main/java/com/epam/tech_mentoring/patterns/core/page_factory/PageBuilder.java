package com.epam.tech_mentoring.patterns.core.page_factory;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverManager;
import com.epam.tech_mentoring.patterns.dsl.pages.BasePage;
import org.openqa.selenium.support.PageFactory;

public class PageBuilder {
    public static  <T extends BasePage> T build (Class<T> clazz) {
        return PageFactory.initElements(WebDriverManager.driver(), clazz);
    }
}

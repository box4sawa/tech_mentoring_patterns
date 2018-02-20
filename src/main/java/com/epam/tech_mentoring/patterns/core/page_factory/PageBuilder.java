package com.epam.tech_mentoring.patterns.core.page_factory;

import com.epam.tech_mentoring.patterns.core.driver_factory.WebDriverManager;
import com.epam.tech_mentoring.patterns.core.page_factory.decorator.CustomFieldDecorator;
import com.epam.tech_mentoring.patterns.dsl.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PageBuilder {
    public static  <T extends BasePage> T build (Class<T> clazz) {
        T page = instantiatePage(WebDriverManager.driver(), clazz);
        ElementLocatorFactory factory = new DefaultElementLocatorFactory(WebDriverManager.driver());
        FieldDecorator decorator = new CustomFieldDecorator(factory);
        PageFactory.initElements(decorator, page);
        return page;
    }

    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

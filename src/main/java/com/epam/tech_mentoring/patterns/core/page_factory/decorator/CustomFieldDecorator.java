package com.epam.tech_mentoring.patterns.core.page_factory.decorator;

import com.epam.tech_mentoring.patterns.core.page_factory.elements.Button;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Input;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Link;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Text;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFieldDecorator extends DefaultFieldDecorator {
    public CustomFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        //Custom Lists
        if (List.class.equals(field.getType())) {
            Type listType = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            if(Link.class.equals(listType)) {
                return proxyForListLocator(loader, locator).stream().map(Link::new).collect(Collectors.toList());
            } else if(Text.class.equals(listType)) {
                return proxyForListLocator(loader, locator).stream().map(Text::new).collect(Collectors.toList());
            } else if(Input.class.equals(listType)) {
                return proxyForListLocator(loader, locator).stream().map(Input::new).collect(Collectors.toList());
            } else if(Button.class.equals(listType)) {
                return proxyForListLocator(loader, locator).stream().map(Button::new).collect(Collectors.toList());
            }
        } else { //Custom elements
            if (Link.class.equals(field.getType())) {
                return new Link(proxyForLocator(loader, locator));
            } else if (Text.class.equals(field.getType())) {
                return new Text(proxyForLocator(loader, locator));
            } else if (Input.class.equals(field.getType())) {
                return new Input(proxyForLocator(loader, locator));
            } else if (Button.class.equals(field.getType())) {
                return new Button(proxyForLocator(loader, locator));
            }
        }
        //Default WebElement
        return super.decorate(loader, field);
    }
}

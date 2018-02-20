package com.epam.tech_mentoring.patterns.core.page_factory.decorator;

import com.epam.tech_mentoring.patterns.core.page_factory.elements.Button;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Input;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Link;
import com.epam.tech_mentoring.patterns.core.page_factory.elements.Text;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.*;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFieldDecorator implements FieldDecorator {
    protected ElementLocatorFactory factory;

    public CustomFieldDecorator(ElementLocatorFactory factory) {
        this.factory = factory;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!(WebElement.class.equals(field.getType())
                || Link.class.equals(field.getType())
                || Text.class.equals(field.getType())
                || Input.class.equals(field.getType())
                || Button.class.equals(field.getType())
                || isDecoratableList(field))) {
            return null;
        }

        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }

        if (WebElement.class.isAssignableFrom(field.getType())) {
            return proxyForLocator(loader, locator);
        } else if (List.class.isAssignableFrom(field.getType())) {
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
            return proxyForListLocator(loader, locator);
        } else if (Link.class.equals(field.getType())) {
            return new Link(proxyForLocator(loader, locator));
        } else if (Text.class.equals(field.getType())) {
            return new Text(proxyForLocator(loader, locator));
        } else if (Input.class.equals(field.getType())) {
            return new Input(proxyForLocator(loader, locator));
        } else if (Button.class.equals(field.getType())) {
            return new Button(proxyForLocator(loader, locator));
        } else {
            return null;
        }
    }

    protected boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }

        // Type erasure in Java isn't complete. Attempt to discover the generic
        // type of the list.
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

        if (!WebElement.class.equals(listType)
                && !Link.class.equals(listType)
                && !Text.class.equals(listType)
                && !Input.class.equals(listType)
                && !Button.class.equals(listType)) {
            return false;
        }

        if (field.getAnnotation(FindBy.class) == null &&
                field.getAnnotation(FindBys.class) == null &&
                field.getAnnotation(FindAll.class) == null) {
            return false;
        }

        return true;
    }

    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementHandler(locator);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(
                loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    @SuppressWarnings("unchecked")
    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);

        List<WebElement> proxy;
        proxy = (List<WebElement>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }
}

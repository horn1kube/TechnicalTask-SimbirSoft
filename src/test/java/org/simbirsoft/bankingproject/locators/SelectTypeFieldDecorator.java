package org.simbirsoft.bankingproject.locators;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Field;

@RequiredArgsConstructor
public class SelectTypeFieldDecorator implements FieldDecorator {
    private final ElementLocatorFactory factory;

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (field.getType().equals(Select.class)) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }
            return new Select(locator.findElement());
        }
        return null;
    }
}

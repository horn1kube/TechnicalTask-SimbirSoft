package org.simbirsoft.bankingproject.locators;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.pagefactory.FieldDecorator;
import org.simbirsoft.bankingproject.locators.annocations.FindTableBy;

import java.lang.reflect.Field;
import java.util.List;

@RequiredArgsConstructor
public class FindTableByAnnotationFieldDecorator implements FieldDecorator {
    private final SearchContext context;

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        FindTableBy annotation;
        if ((annotation = field.getAnnotation(FindTableBy.class)) != null) {
            if (annotation.how() == How.XPATH) {
                List<WebElement> rows = context.findElements(By.xpath(annotation.using()));
                return rows.stream().map((elem) -> elem.findElements(By.tagName("td"))).toList();
            }
        }
        return null;
    }
}

package org.simbirsoft.bankingproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.simbirsoft.bankingproject.locators.FindTableByAnnotationFieldDecorator;
import org.simbirsoft.bankingproject.locators.SelectTypeFieldDecorator;

public abstract class BasePage extends LoadableComponent<BasePage> {
    protected WebDriver webDriver;

    protected BasePage(WebDriver webDriver) {
        init(webDriver);
        get();
    }

    public void init(final WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
        ElementLocatorFactory elementLocatorFactory = new DefaultElementLocatorFactory(driver);
        PageFactory.initElements(new FindTableByAnnotationFieldDecorator(driver), this);
        PageFactory.initElements(new SelectTypeFieldDecorator(elementLocatorFactory), this);
    }
}

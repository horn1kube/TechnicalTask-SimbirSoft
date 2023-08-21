package org.simbirsoft.bankingproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver webDriver;

    public void init(final WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
}

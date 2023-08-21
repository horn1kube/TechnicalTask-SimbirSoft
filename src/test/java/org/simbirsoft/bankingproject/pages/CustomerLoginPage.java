package org.simbirsoft.bankingproject.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@RequiredArgsConstructor
public class CustomerLoginPage {
    private final WebDriver webDriver;
    public Select selectCustomerButton() {
        return new Select(webDriver.findElement(By.id("userSelect")));
    }

    public WebElement loginButton() {
        return webDriver.findElement(By.xpath("//button[contains(text(),'Login')]"));
    }
}

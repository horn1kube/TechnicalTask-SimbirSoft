package org.simbirsoft.bankingproject.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RequiredArgsConstructor
public class AccountPage {
    private final WebDriver webDriver;

    public WebElement depositButton() {
        return webDriver.findElement(By.xpath("//button[contains(text(),'Deposit')]"));
    }

    public WebElement depositInput() {
        return webDriver.findElement(By.xpath("//input[@label='Amount to be Deposited :']"));
    }

    public int balance() {
        return Integer.parseInt(webDriver.findElements(By.xpath("//div[@ng-hide='noAccount']//strong[@class='ng-binding']")).get(1).getText());
    }
}

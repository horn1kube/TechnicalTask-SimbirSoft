package org.simbirsoft.bankingproject.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@RequiredArgsConstructor
public class LoginPage {
    private final WebDriver webDriver;

    public WebElement customerLoginButton() {
        return webDriver.findElement(By.xpath("//button[contains(text(),'Customer Login')]"));
    }
}

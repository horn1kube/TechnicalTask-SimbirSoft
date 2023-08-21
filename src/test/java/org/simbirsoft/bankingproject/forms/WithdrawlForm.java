package org.simbirsoft.bankingproject.forms;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class WithdrawlForm {
    public WithdrawlForm(WebDriver webDriver) {
        amountInput = webDriver.findElement(By.xpath("//form[@name='myForm']//input[@placeholder='amount']"));
        withdrawlButton = webDriver.findElement(By.xpath("//form[@name='myForm']//button[@type='submit']"));
    }
    private final WebElement amountInput;
    private final WebElement withdrawlButton;
}

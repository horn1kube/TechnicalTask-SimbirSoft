package org.simbirsoft.bankingproject.forms;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Getter
public class DepositForm {
    public DepositForm(WebDriver webDriver) {
        amountInput = webDriver.findElement(By.xpath("//form[@name='myForm']//input[@placeholder='amount']"));
        depositButton = webDriver.findElement(By.xpath("//form[@name='myForm']//button[@type='submit']"));
    }
    private final WebElement amountInput;
    private final WebElement depositButton;
}

package org.simbirsoft.bankingproject.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

@Getter
public class CustomerLoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    public Select selectCustomerButton() {
        return new Select(webDriver.findElement(By.id("userSelect")));
    }
}

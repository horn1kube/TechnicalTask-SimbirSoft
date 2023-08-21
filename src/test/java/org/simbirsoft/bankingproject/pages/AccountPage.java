package org.simbirsoft.bankingproject.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class AccountPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Deposit')]")
    private WebElement depositButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Withdrawl')]")
    private WebElement withdrawlButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Transactions')]")
    private WebElement transactionsButton;

    public int balance() {
        return Integer.parseInt(webDriver.findElements(By.xpath("//div[@ng-hide='noAccount']//strong[@class='ng-binding']")).get(1).getText());
    }
}

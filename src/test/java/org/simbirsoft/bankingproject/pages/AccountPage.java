package org.simbirsoft.bankingproject.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class AccountPage extends BasePage<AccountPage> {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Deposit')]")
    private WebElement depositButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Withdrawl')]")
    private WebElement withdrawlButton;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Transactions')]")
    private WebElement transactionsButton;

    @FindBy(how = How.XPATH, using = "//div[@ng-hide='noAccount']//strong[@class='ng-binding'][2]")
    private WebElement balance;

    @FindBy(how = How.XPATH, using = "//form[@name='myForm']//input[@placeholder='amount']")
    private WebElement formAmountInput;

    @FindBy(how = How.XPATH, using = "//form[@name='myForm']//button[@type='submit']")
    private WebElement formSubmitButton;

    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(withdrawlButton),
                        ExpectedConditions.visibilityOf(depositButton),
                        ExpectedConditions.visibilityOf(transactionsButton)
                ));
    }

    public void isWithdrawlFormLoaded() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(formAmountInput),
                        ExpectedConditions.visibilityOf(formSubmitButton),
                        ExpectedConditions.textToBePresentInElement(formSubmitButton, "Withdraw")
                ));
    }

    public void isDepositFormLoaded() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(formAmountInput),
                        ExpectedConditions.visibilityOf(formSubmitButton),
                        ExpectedConditions.textToBePresentInElement(formSubmitButton, "Deposit")
                ));
    }
}

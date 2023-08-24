package org.simbirsoft.bankingproject.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_POLLING_DURATION;
import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_WAIT_TIMEOUT;

public class AccountPage extends BasePage {
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
        new WebDriverWait(webDriver, DEFAULT_WAIT_TIMEOUT)
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(withdrawlButton),
                        ExpectedConditions.visibilityOf(depositButton),
                        ExpectedConditions.visibilityOf(transactionsButton)
                ));
    }

    @Step("Проверка, прогружена ли форма снятия")
    public void isWithdrawlFormLoaded() {
        new WebDriverWait(webDriver, DEFAULT_WAIT_TIMEOUT)
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(formAmountInput),
                        ExpectedConditions.visibilityOf(formSubmitButton),
                        ExpectedConditions.textToBePresentInElement(formSubmitButton, "Withdraw")
                ));
    }

    @Step("Проверка, прогружена ли форма депозита")
    public void isDepositFormLoaded() {
        new WebDriverWait(webDriver, DEFAULT_WAIT_TIMEOUT)
                .until(ExpectedConditions.and(
                        ExpectedConditions.visibilityOf(formAmountInput),
                        ExpectedConditions.visibilityOf(formSubmitButton),
                        ExpectedConditions.textToBePresentInElement(formSubmitButton, "Deposit")
                ));
    }

    @Step("Нажатие кнопки \"Deposit\"")
    public void clickDepositButton() {
        depositButton.click();
    }

    @Step("Ввод {amount} в поле amount")
    public void setFormAmount(int amount) {
        formAmountInput.sendKeys(String.valueOf(amount));
    }

    @Step("Нажатие submit-кнопки")
    public void clickSubmitButton() {
        formSubmitButton.click();
    }

    @Step("Нажатие кнопки \"Withdrawl\"")
    public void clickWithdrawlButton() {
        withdrawlButton.click();
    }

    @Step("Нажатие кнопки \"Transactions\"")
    public void clickTransactionsButton() {
        transactionsButton.click();
    }

    @Step("Депозит {value} на счёт")
    public void deposit(int value) {
        int moneyBefore = getBalance();
        clickDepositButton();
        isDepositFormLoaded();
        setFormAmount(value);
        clickSubmitButton();
        FluentWait<AccountPage> wait = new FluentWait<>(this)
                .withTimeout(DEFAULT_WAIT_TIMEOUT)
                .pollingEvery(DEFAULT_POLLING_DURATION)
                .ignoring(IllegalStateException.class);
        wait.until((page) -> page.getBalance() == moneyBefore + value);
    }

    @Step("Снятие {value} со счёта")
    public void withdraw(int value) {
        int moneyBefore = getBalance();
        clickWithdrawlButton();
        isWithdrawlFormLoaded();
        setFormAmount(value);
        clickSubmitButton();
        FluentWait<AccountPage> wait = new FluentWait<>(this)
                .withTimeout(DEFAULT_WAIT_TIMEOUT)
                .pollingEvery(DEFAULT_POLLING_DURATION)
                .ignoring(IllegalStateException.class);
        wait.until((page) -> page.getBalance() == moneyBefore - value);
    }

    public int getBalance() {
        return Integer.parseInt(balance.getText());
    }
}

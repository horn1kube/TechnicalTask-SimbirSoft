package org.simbirsoft.bankingproject;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.simbirsoft.bankingproject.config.AccountPageConfig;
import org.simbirsoft.bankingproject.config.TransactionsPageConfig;
import org.simbirsoft.bankingproject.model.Customer;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.pages.AccountPage;
import org.simbirsoft.bankingproject.pages.CustomerLoginPage;
import org.simbirsoft.bankingproject.pages.LoginPage;
import org.simbirsoft.bankingproject.pages.TransactionsPage;
import org.simbirsoft.bankingproject.utils.PageLoader;

import java.net.MalformedURLException;
import java.util.List;

import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_POLLING_DURATION;
import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_WAIT_TIMEOUT;
import static org.simbirsoft.bankingproject.utils.DateTimeUtils.getDayOfMonth;
import static org.simbirsoft.bankingproject.utils.FilesAttachments.attachTransactionsList;
import static org.simbirsoft.bankingproject.utils.MathUtils.countFibonacciNumber;
import static org.simbirsoft.bankingproject.utils.TransactionsUtils.checkCountOfTransactions;
import static org.simbirsoft.bankingproject.utils.TransactionsUtils.createTwoTransactions;

@Epic("Customer menu tests")
public class CustomerMenuTest extends BaseSeleniumTest {
    @Override
    @BeforeEach
    public void setUp() throws MalformedURLException, InterruptedException {
        super.setUp();
        login();
    }

    private void login() {
        FluentWait<WebDriver> wait = new FluentWait<>(chromeDriver)
                .withTimeout(DEFAULT_WAIT_TIMEOUT)
                .pollingEvery(DEFAULT_POLLING_DURATION);
        LoginPage loginPage = PageLoader.loadLoginPage(chromeDriver);
        loginPage.clickCustomerLoginButton();
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(chromeDriver);
        customerLoginPage.selectCustomer(Customer.HARRY_POTTER);
        customerLoginPage.clickLoginButton();
        wait.until(ExpectedConditions.urlToBe(AccountPageConfig.URL));
    }

    @Test
    @Feature("Deposit")
    @Story("Successful deposit")
    @Description("Harry Potter tries to deposit fibonacci number")
    public void depositTest() {
        AccountPage accountPage = new AccountPage(chromeDriver);
        int fibonacci = countFibonacciNumber(getDayOfMonth());
        accountPage.deposit(fibonacci);
        Assertions.assertThat(accountPage.getBalance()).isEqualTo(fibonacci);
    }


    @Test
    @Story("Successful withdraw")
    @Description("Harry Potter tries to withdraw fibonacci number")
    public void withdrawlTest() {
        AccountPage accountPage = new AccountPage(chromeDriver);
        int fibonacci = countFibonacciNumber(getDayOfMonth());
        initStartBalance(accountPage, fibonacci);
        accountPage.withdraw(fibonacci);
        Assertions.assertThat(accountPage.getBalance()).isEqualTo(0);
    }

    @Step("Установка стартового баланса в размере {balance}")
    private void initStartBalance(AccountPage accountPage, int balance) {
        accountPage.deposit(balance);
    }


    @Test
    @Story("Transactions successfully received")
    @Description("Harry Potter tries to get all transactions in account")
    public void transactionsTest() {
        AccountPage accountPage = new AccountPage(chromeDriver);
        createTwoTransactions(accountPage);
        accountPage.clickTransactionsButton();
        FluentWait<WebDriver> wait = new FluentWait<>(chromeDriver)
                .withTimeout(DEFAULT_WAIT_TIMEOUT)
                .pollingEvery(DEFAULT_POLLING_DURATION);
        wait.until(ExpectedConditions.urlToBe(TransactionsPageConfig.URL));
        TransactionsPage transactionsPage = new TransactionsPage(chromeDriver);
        transactionsPage.isTransactionsLoaded(2);
        transactionsPage.init(chromeDriver);
        List<Transaction> transactions = transactionsPage.transactions();
        checkCountOfTransactions(transactions, 2);
        attachTransactionsList(transactions);
    }
}

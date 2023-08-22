package org.simbirsoft.bankingproject;

import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.bankingproject.config.SeleniumConfig;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.pages.AccountPage;
import org.simbirsoft.bankingproject.pages.CustomerLoginPage;
import org.simbirsoft.bankingproject.pages.LoginPage;
import org.simbirsoft.bankingproject.pages.TransactionsPage;
import org.simbirsoft.bankingproject.utils.Files;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.MethodOrderer.*;


@TestMethodOrder(OrderAnnotation.class)
@Epic("Login Tests Epic")
@Feature("Valid Harry Potter login")
public class LoginTest {
    private static WebDriver chromeDriver;
    private static int fibonacci;
    private static List<Transaction> transactions;
    public static Wait<WebDriver> wait;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        chromeDriver = new RemoteWebDriver(new URL(SeleniumConfig.SELENIUM_GRID_HUB_URL), new ChromeOptions());
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        chromeDriver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        chromeDriver.quit();
    }

    @Test
    @Order(1)
    @Story("Harry Potter tries to login")
    @Description("Valid login")
    public void customerLoginTest() {
        wait = new FluentWait<>(chromeDriver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        LoginPage loginPage = loadPage();
        Allure.step("Нажатие кнопки \"Customer Login\"");
        clickCustomerLoginButton(loginPage);
        wait.until(ExpectedConditions.urlToBe("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer"));
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(chromeDriver);
        Allure.step("Выбор аккаунта \"Harry Potter\"");
        selectHarryPotterAccount(customerLoginPage);
        Allure.step("Нажатие кнопки \"Login\"");
        clickLoginButton(customerLoginPage);
        wait.until(ExpectedConditions.urlToBe("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account"));
    }

    @Step("Загрузка страницы " + SeleniumConfig.LOGIN_PAGE_URL)
    public LoginPage loadPage() {
        chromeDriver.get(SeleniumConfig.LOGIN_PAGE_URL);
        return new LoginPage(chromeDriver);
    }

    public void clickCustomerLoginButton(LoginPage loginPage) {
        loginPage.getCustomerLoginButton().click();
    }

    public void selectHarryPotterAccount(CustomerLoginPage customerLoginPage) {
        customerLoginPage.getSelectCustomerButton().selectByVisibleText("Harry Potter");
    }

    public void clickLoginButton(CustomerLoginPage customerLoginPage) {
        customerLoginPage.getLoginButton().click();
    }

    @Test
    @Order(2)
    @Story("Harry Potter tries to deposit fibonacci number")
    @Description("Successful deposit")
    public void depositTest() {
        AccountPage accountPage = new AccountPage(chromeDriver);
        accountPage.getDepositButton().click();
        accountPage.isDepositFormLoaded();
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        fibonacci = fib(dayOfMonth);
        accountPage.getFormAmountInput().sendKeys(String.valueOf(fibonacci));
        accountPage.getFormSubmitButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(accountPage.getBalance(),String.valueOf(fibonacci)));
    }

    @Test
    @Order(3)
    @Story("Harry Potter tries to withdraw fibonacci number")
    @Description("Successful withdraw")
    public void withdrawlTest() {
        AccountPage accountPage = new AccountPage(chromeDriver);
        accountPage.getWithdrawlButton().click();
        accountPage.isWithdrawlFormLoaded();
        accountPage.getFormAmountInput().sendKeys(String.valueOf(fibonacci));
        accountPage.getFormSubmitButton().click();
        wait.until(ExpectedConditions.textToBePresentInElement(accountPage.getBalance(),"0"));
    }

    @Test
    @Order(4)
    @Story("Harry Potter tries to get all transactions in account")
    @Description("Transactions successfully received")
    public void transactionsTest() throws IOException {
        AccountPage accountPage = new AccountPage(chromeDriver);
        accountPage.getTransactionsButton().click();
        wait.until(ExpectedConditions.urlToBe("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/listTx"));
        TransactionsPage transactionsPage = new TransactionsPage(chromeDriver);
        transactions = transactionsPage.transactions();
        Assertions.assertThat(transactions).hasSize(2);
        transactionsListAttachment();
    }

    @Attachment(value = "Transactions list", type = "text/csv", fileExtension = ".csv")
    public String transactionsListAttachment() throws IOException {
        return Files.writeTransactionsInCSV(transactions);
    }

    private static int fib(int n) {
        int a = 0, b = 1, c;
        if (n == 0) return a;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}

package org.simbirsoft.bankingproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.simbirsoft.bankingproject.config.SeleniumConfig;
import org.simbirsoft.bankingproject.forms.DepositForm;
import org.simbirsoft.bankingproject.forms.WithdrawlForm;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.pages.AccountPage;
import org.simbirsoft.bankingproject.pages.CustomerLoginPage;
import org.simbirsoft.bankingproject.pages.LoginPage;
import org.simbirsoft.bankingproject.pages.TransactionsPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.MethodOrderer.*;


@TestMethodOrder(OrderAnnotation.class)
public class LoginTest {
    private static WebDriver chromeDriver;
    private static int fibonacci;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        chromeDriver = new RemoteWebDriver(new URL(SeleniumConfig.SELENIUM_GRID_HUB_URL), new ChromeOptions());
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        chromeDriver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        chromeDriver.quit();
    }

    @Test
    @Order(1)
    public void customerLoginTest() throws InterruptedException {
        chromeDriver.get(SeleniumConfig.LOGIN_PAGE_URL);
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage(chromeDriver);
        loginPage.customerLoginButton().click();
        Thread.sleep(3000);
        Assertions.assertThat(chromeDriver.getCurrentUrl()).isEqualTo("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(chromeDriver);
        customerLoginPage.selectCustomerButton().selectByVisibleText("Harry Potter");
        Thread.sleep(3000);
        customerLoginPage.loginButton().click();
        Thread.sleep(3000);
        Assertions.assertThat(chromeDriver.getCurrentUrl()).isEqualTo("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account");
    }

    @Test
    @Order(2)
    public void depositTest() throws InterruptedException {
        AccountPage accountPage = new AccountPage(chromeDriver);
        accountPage.depositButton().click();
        Thread.sleep(3000);
        DepositForm depositForm = new DepositForm(chromeDriver);
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        fibonacci = fib(dayOfMonth);
        depositForm.getAmountInput().sendKeys(String.valueOf(fibonacci));
        depositForm.getDepositButton().click();
        Thread.sleep(3000);
        Assertions.assertThat(accountPage.balance()).isEqualTo(fibonacci);
    }

    @Test
    @Order(3)
    public void withdrawlTest() throws InterruptedException {
        AccountPage accountPage = new AccountPage(chromeDriver);
        accountPage.withdrawlButton().click();
        Thread.sleep(3000);
        WithdrawlForm withdrawlForm = new WithdrawlForm(chromeDriver);
        withdrawlForm.getAmountInput().sendKeys(String.valueOf(fibonacci));
        withdrawlForm.getWithdrawlButton().click();
        Thread.sleep(3000);
        Assertions.assertThat(accountPage.balance()).isEqualTo(0);
    }

    @Test
    @Order(4)
    public void transactionsTest() throws InterruptedException {
        AccountPage accountPage = new AccountPage(chromeDriver);
        accountPage.transactionsButton().click();
        Thread.sleep(3000);
        TransactionsPage transactionsPage = new TransactionsPage(chromeDriver);
        List<Transaction> transactions = transactionsPage.transactions();
        Assertions.assertThat(transactions).hasSize(2);
    }

    private static int fib(int n) {
        int a = 0, b = 1, c;
        if (n == 0)
            return a;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}

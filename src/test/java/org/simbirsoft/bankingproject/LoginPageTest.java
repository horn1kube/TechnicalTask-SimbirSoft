package org.simbirsoft.bankingproject;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.simbirsoft.bankingproject.config.SeleniumConfig;
import org.simbirsoft.bankingproject.pages.CustomerLoginPage;
import org.simbirsoft.bankingproject.pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginPageTest {
    private WebDriver chromeDriver;

    @Before
    public void setUp() throws MalformedURLException {
        chromeDriver = new RemoteWebDriver(new URL(SeleniumConfig.SELENIUM_GRID_HUB_URL), new ChromeOptions());
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        chromeDriver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        chromeDriver.quit();
    }

    @Test
    public void customerLoginTest() throws InterruptedException {
        chromeDriver.get(SeleniumConfig.LOGIN_PAGE_URL);
        LoginPage loginPage = new LoginPage(chromeDriver);
        WebElement loginButton = loginPage.customerLoginButton();
        loginButton.click();
        Thread.sleep(500);
        Assertions.assertThat(chromeDriver.getCurrentUrl()).isEqualTo("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer");
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(chromeDriver);
        customerLoginPage.selectCustomerButton().selectByVisibleText("Harry Potter");
        Thread.sleep(500);
        customerLoginPage.loginButton().click();
        Thread.sleep(500);
        Assertions.assertThat(chromeDriver.getCurrentUrl()).isEqualTo("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account");
    }
}

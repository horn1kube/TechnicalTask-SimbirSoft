package org.simbirsoft.bankingproject;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.simbirsoft.bankingproject.config.AccountPageConfig;
import org.simbirsoft.bankingproject.config.CustomerPageConfig;
import org.simbirsoft.bankingproject.model.Customer;
import org.simbirsoft.bankingproject.pages.CustomerLoginPage;
import org.simbirsoft.bankingproject.pages.LoginPage;
import org.simbirsoft.bankingproject.utils.PageLoader;

import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_POLLING_DURATION;
import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_WAIT_TIMEOUT;


@Epic("Login tests")
@Feature("Customer login")
public class CustomerLoginTest extends BaseSeleniumTest {
    @Test
    @Story("Valid login")
    @Description("Harry Potter tries to login")
    public void customerLoginTest() {
        FluentWait<WebDriver> wait = new FluentWait<>(chromeDriver)
                .withTimeout(DEFAULT_WAIT_TIMEOUT)
                .pollingEvery(DEFAULT_POLLING_DURATION)
                .ignoring(IllegalStateException.class);
        LoginPage loginPage = PageLoader.loadLoginPage(chromeDriver);
        loginPage.clickCustomerLoginButton();
        wait.until(ExpectedConditions.urlToBe(CustomerPageConfig.URL));
        CustomerLoginPage customerLoginPage = new CustomerLoginPage(chromeDriver);
        customerLoginPage.selectCustomer(Customer.HARRY_POTTER);
        customerLoginPage.clickLoginButton();
        wait.until(ExpectedConditions.urlToBe(AccountPageConfig.URL));
    }
}

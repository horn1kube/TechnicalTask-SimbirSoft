package org.simbirsoft.bankingproject.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.bankingproject.config.SeleniumConfig;
import org.simbirsoft.bankingproject.model.Customer;

@Getter
public class CustomerLoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    @FindBy(how = How.ID, using = "userSelect")
    private Select selectCustomerButton;

    public CustomerLoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        new WebDriverWait(webDriver, SeleniumConfig.DEFAULT_WAIT_TIMEOUT)
                .until(ExpectedConditions.or(
                        ExpectedConditions.and(
                                ExpectedConditions.visibilityOf(selectCustomerButton.getWrappedElement()),
                                ExpectedConditions.not(ExpectedConditions.visibilityOf(loginButton))),
                        ExpectedConditions.and(
                                ExpectedConditions.visibilityOf(loginButton),
                                ExpectedConditions.visibilityOf(selectCustomerButton.getWrappedElement()))
                ));
    }

    @Step("Выбор аккаунта \"{customer}\"")
    public void selectCustomer(Customer customer) {
        selectCustomerButton.selectByVisibleText(customer.toString());
    }

    @Step("Нажатие кнопки \"Login\"")
    public void clickLoginButton() {
        loginButton.click();
    }
}

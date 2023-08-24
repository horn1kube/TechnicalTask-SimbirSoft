package org.simbirsoft.bankingproject.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.bankingproject.config.SeleniumConfig;

@Getter
public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Customer Login')]")
    private WebElement customerLoginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void load() {
        init(webDriver);
    }

    @Override
    protected void isLoaded() throws Error {
        new WebDriverWait(webDriver, SeleniumConfig.DEFAULT_WAIT_TIMEOUT).until(ExpectedConditions.visibilityOf(customerLoginButton));
    }

    @Step("Нажатие кнопки \"Customer Login\"")
    public void clickCustomerLoginButton() {
        customerLoginButton.click();
    }
}

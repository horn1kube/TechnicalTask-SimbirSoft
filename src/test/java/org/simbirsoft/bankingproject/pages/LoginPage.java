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
public class LoginPage extends BasePage<LoginPage> {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Customer Login')]")
    private WebElement customerLoginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {
        new WebDriverWait(webDriver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(customerLoginButton));
    }
}

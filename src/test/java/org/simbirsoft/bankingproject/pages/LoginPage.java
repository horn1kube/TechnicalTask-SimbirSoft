package org.simbirsoft.bankingproject.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@Getter
public class LoginPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Customer Login')]")
    private WebElement customerLoginButton;
}

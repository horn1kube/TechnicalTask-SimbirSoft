package org.simbirsoft.bankingproject.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.simbirsoft.bankingproject.config.LoginPageConfig;
import org.simbirsoft.bankingproject.pages.LoginPage;

public class PageLoader {
    @Step("Загрузка страницы " + LoginPageConfig.URL)
    public static LoginPage loadLoginPage(WebDriver driver) {
        driver.get(LoginPageConfig.URL);
        return new LoginPage(driver);
    }
}

package org.simbirsoft.bankingproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.simbirsoft.bankingproject.config.SeleniumConfig;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseSeleniumTest {
    protected WebDriver chromeDriver;

    @BeforeEach
    public void setUp() throws MalformedURLException, InterruptedException {
        chromeDriver = new RemoteWebDriver(new URL(SeleniumConfig.SELENIUM_GRID_HUB_URL), SeleniumConfig.DEFAULT_CHROME_OPTIONS);
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().deleteAllCookies();
    }

    @AfterEach
    public void tearDown() {
        chromeDriver.quit();
    }
}

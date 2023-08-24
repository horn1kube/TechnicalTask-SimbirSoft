package org.simbirsoft.bankingproject.config;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;

import java.time.Duration;

public class SeleniumConfig {
    public static final String SELENIUM_GRID_HUB_URL = "http://localhost:4444/";
    public static final ChromiumOptions<?> DEFAULT_CHROME_OPTIONS = new ChromeOptions()
            .setPageLoadTimeout(Duration.ofSeconds(20))
            .setScriptTimeout(Duration.ofSeconds(20))
            .setImplicitWaitTimeout(Duration.ofSeconds(20));
    public static final Duration DEFAULT_WAIT_TIMEOUT = Duration.ofSeconds(5);
    public static final Duration DEFAULT_POLLING_DURATION = Duration.ofSeconds(1);
}

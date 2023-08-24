package org.simbirsoft.bankingproject.pages;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.bankingproject.config.SeleniumConfig;
import org.simbirsoft.bankingproject.locators.annocations.FindTableBy;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.model.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.simbirsoft.bankingproject.config.SeleniumConfig.DEFAULT_POLLING_DURATION;

@Getter
public class TransactionsPage extends BasePage<TransactionsPage> {

    @FindTableBy(how = How.XPATH, using = "//table//tbody//tr")
    private List<List<WebElement>> transactionsTableRows;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Back')]")
    private WebElement backButton;

    public TransactionsPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Получение транзакций")
    public List<Transaction> transactions() {
        return transactionsTableRows.stream().map((elem) -> {
            List<String> row = elem.stream().map(WebElement::getText).toList();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss a", Locale.ENGLISH);
            return Transaction.builder()
                    .dateTime(LocalDateTime.parse(row.get(0), formatter))
                    .amount(Integer.parseInt(row.get(1)))
                    .type(TransactionType.valueOf(row.get(2).toUpperCase()))
                    .build();
        }).toList();
    }

    @Override
    protected void load() {

    }

    @Step("Ожидание загрузки страницы с транзакциями")
    @Override
    protected void isLoaded() {
        new WebDriverWait(webDriver, SeleniumConfig.DEFAULT_WAIT_TIMEOUT).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(backButton)));
    }

    @Step("Ожидание загрузки минимум {count} транзакций")
    public void isTransactionsLoaded(int count) {
        new WebDriverWait(webDriver, SeleniumConfig.DEFAULT_WAIT_TIMEOUT).pollingEvery(DEFAULT_POLLING_DURATION).until((webDriver) -> {
            webDriver.navigate().refresh();
            new WebDriverWait(webDriver, SeleniumConfig.DEFAULT_WAIT_TIMEOUT).pollingEvery(DEFAULT_POLLING_DURATION)
                    .until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
            return webDriver.findElements(By.xpath("//table//tbody//tr")).size() >= count;
        });
    }
}

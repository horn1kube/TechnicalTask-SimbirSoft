package org.simbirsoft.bankingproject.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.bankingproject.locators.annocations.FindTableBy;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.model.TransactionType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Getter
public class TransactionsPage extends BasePage<TransactionsPage> {

    @FindTableBy(how = How.XPATH, using = "//table//tbody//tr")
    private List<List<WebElement>> transactionsTableRows;

    public TransactionsPage(WebDriver webDriver) {
        super(webDriver);
    }

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

    @Override
    protected void isLoaded() throws Error {
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table//tbody//tr")));
    }
}

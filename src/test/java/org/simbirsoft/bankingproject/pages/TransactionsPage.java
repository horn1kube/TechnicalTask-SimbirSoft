package org.simbirsoft.bankingproject.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.model.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
public class TransactionsPage {
    private final WebDriver webDriver;

    public List<Transaction> transactions() {
        return webDriver.findElements(By.xpath("//table//tbody//tr")).stream().map((elem) -> {
            List<String> row = elem.findElements(By.tagName("td")).stream().map(WebElement::getText).toList();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy h:mm:ss a", Locale.ENGLISH);
            return Transaction.builder()
                    .dateTime(LocalDateTime.parse(row.get(0), formatter))
                    .amount(Integer.parseInt(row.get(1)))
                    .type(TransactionType.valueOf(row.get(2).toUpperCase()))
                    .build();
        }).toList();
    }
}

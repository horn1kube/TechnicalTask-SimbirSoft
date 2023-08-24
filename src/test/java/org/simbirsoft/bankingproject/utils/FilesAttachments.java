package org.simbirsoft.bankingproject.utils;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.simbirsoft.bankingproject.model.Transaction;

import java.util.List;

public class FilesAttachments {
    @Step("Прикрепление csv-файла с транзакциями")
    @Attachment(value = "Transactions list", type = "text/csv", fileExtension = ".csv")
    @SneakyThrows
    public static String attachTransactionsList(List<Transaction> transactions) {
        return Files.writeTransactionsInCSV(transactions);
    }
}

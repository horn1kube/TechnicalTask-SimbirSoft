package org.simbirsoft.bankingproject.utils;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.simbirsoft.bankingproject.model.Transaction;
import org.simbirsoft.bankingproject.pages.AccountPage;

import java.util.List;

import static org.simbirsoft.bankingproject.utils.DateTimeUtils.getDayOfMonth;
import static org.simbirsoft.bankingproject.utils.MathUtils.countFibonacciNumber;

public class TransactionsUtils {
    @Step("Проверка, что количество транзакций равно {expected}")
    public static void checkCountOfTransactions(List<Transaction> transactions, int expected) {
        Assertions.assertThat(transactions).hasSize(expected);
    }

    @Step("Создание двух транзакций (депозит и снятие)")
    public static void createTwoTransactions(AccountPage accountPage) {
        int value = countFibonacciNumber(getDayOfMonth());
        accountPage.deposit(value);
        accountPage.withdraw(value);
    }
}

package org.simbirsoft.bankingproject.utils;

import io.qameta.allure.Step;

import java.time.LocalDateTime;

public class DateTimeUtils {
    @Step("Получение текущего дня в месяце")
    public static int getDayOfMonth() {
        return LocalDateTime.now().getDayOfMonth();
    }
}

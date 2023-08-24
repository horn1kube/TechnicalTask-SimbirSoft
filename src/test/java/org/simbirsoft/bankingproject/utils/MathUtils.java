package org.simbirsoft.bankingproject.utils;

import io.qameta.allure.Step;

public class MathUtils {
    @Step("Вычисление {dayOfMonth} числа Фибоначчи ")
    public static int countFibonacciNumber(int dayOfMonth) {
        return fib(dayOfMonth);
    }

    private static int fib(int n) {
        int a = 0, b = 1, c;
        if (n == 0) return a;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}

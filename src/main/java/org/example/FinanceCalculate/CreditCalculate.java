package org.example.FinanceCalculate;

import lombok.extern.java.Log;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CreditCalculate {
    public static void result(Update update, SendMessage message, long chatId) {
        String messageText = update.getMessage().getText();

        // Разбиваем входное сообщение и создаем список
        String[] parsedCreditMessageList = messageText.split(" ");
        ArrayList<String> creditMessageList = new ArrayList<>(List.of(parsedCreditMessageList));

        // Проверка, что пользователь ввел достаточно аргументов
        if (creditMessageList.size() < 4) {
            // Обработка ошибки: недостаточно аргументов
            message.setText("Пожалуйста, введите сумму кредита, процент и число месяцев (например, /creditCalculate 1000000 21 12).");
            Logger.logSend(message,chatId);
            return;
        }

        try {
            // Преобразуем введенные данные в числа
            int creditAmount = Integer.parseInt(creditMessageList.get(1));
            int creditPercent = Integer.parseInt(creditMessageList.get(2));
            int creditMonths = Integer.parseInt(creditMessageList.get(3));

            // Рассчитываем общую сумму выплаты, переплату и ежемесячный платеж
            double monthlyRate = (double) creditPercent / 100 / 12; // Процентная ставка в месяц
            double monthlyPayment = (creditAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -creditMonths));
            double totalPayment = monthlyPayment * creditMonths;
            double overpayment = totalPayment - creditAmount;

            // Формируем ответ
            String responseMessage = String.format(Locale.ENGLISH,
                    "\uD83D\uDCB0Ежемесячный платеж: %.2f\n\uD83D\uDCB3Сумма, которую нужно выплатить: %.2f\n\uD83D\uDCB8Переплата: %.2f",
                    monthlyPayment, totalPayment, overpayment);
            message.setText(responseMessage);
            Logger.logSend(message,chatId);
        } catch (NumberFormatException e) {
            // Обработка ошибки: неверный формат числа
            message.setText("Ошибка ввода. Убедитесь, что сумма кредита, процент и срок введены правильно.");
            Logger.logSend(message,chatId);
        }
    }
}

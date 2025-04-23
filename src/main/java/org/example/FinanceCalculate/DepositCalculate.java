package org.example.FinanceCalculate;

import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DepositCalculate {
    public static void result(Update update, SendMessage message, long chatId) {
        String messageText = update.getMessage().getText();

        // Разбиваем входное сообщение и создаем список
        String[] parsedDepositMessageList = messageText.split(" ");
        ArrayList<String> depositMessageList = new ArrayList<>(List.of(parsedDepositMessageList));

        // Проверка, что пользователь ввел достаточно аргументов
        if (depositMessageList.size() < 4) {
            // Обработка ошибки: недостаточно аргументов
            message.setText("Пожалуйста, введите сумму вклада, процент и число месяцев (например, /depositCalculate 1000000 5 36).");
            Logger.logSend(message, chatId);
            return;
        }

        try {
            // Преобразуем введенные данные в числа
            double depositAmount = Double.parseDouble(depositMessageList.get(1));
            double depositPercent = Double.parseDouble(depositMessageList.get(2));
            int depositMonths = Integer.parseInt(depositMessageList.get(3)); // Теперь читаем месяцы

            // Рассчитываем итоговую сумму (с учетом месяцев)
            double totalAmount = depositAmount + (depositAmount * depositPercent / 100 * (depositMonths / 12.0)); // Делим на 12 для перевода в годы
            double profit = totalAmount - depositAmount;  // Прибыль от вклада

            // Формируем ответ
            String responseMessage = String.format(Locale.ENGLISH,
                    "Итоговая сумма: %.2f\nВ плюсе: %.2f",
                    totalAmount, profit);
            message.setText(responseMessage);
            Logger.logSend(message, chatId);
        } catch (NumberFormatException e) {
            // Обработка ошибки: неверный формат числа
            message.setText("Ошибка ввода. Убедитесь, что сумма вклада, процент и срок введены правильно.");
            Logger.logSend(message, chatId);
        }
    }
}

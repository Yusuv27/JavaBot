package org.example.Functional.FinanceCalculate;

import org.example.Functional.ToolsString.ToolsString;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DepositCalculate {
    public static void result(Update update, SendMessage message, long chatId) {
        String messageText = update.getMessage().getText();


        String[] parsedDepositMessageList = messageText.split(" ");
        ArrayList<String> depositMessageList = new ArrayList<>(List.of(parsedDepositMessageList));

        if (depositMessageList.size() < 4) {
            message.setText("Пожалуйста, введите сумму вклада, процент и число месяцев (например, /depositCalculate 1000000 5 36).");
            Logger.logSend(message, chatId);
            return;
        }

        try {

            double depositAmount = Double.parseDouble(depositMessageList.get(1));
            double depositPercent = Double.parseDouble(depositMessageList.get(2));
            int depositMonths = Integer.parseInt(depositMessageList.get(3)); // Теперь читаем месяцы

            double totalAmount = depositAmount + (depositAmount * depositPercent / 100 * (depositMonths / 12.0)); // Делим на 12 для перевода в годы
            double profit = totalAmount - depositAmount;  // Прибыль от вклада

            String responseMessage = String.format(Locale.ENGLISH,
                    "Итоговая сумма: %.2f\nВ плюсе: %.2f",
                    totalAmount, profit);

            ToolsString.addFunc(message,responseMessage,chatId);

        } catch (NumberFormatException e) {

            message.setText("Ошибка ввода. Убедитесь, что сумма вклада, процент и срок введены правильно.");
            Logger.logSend(message, chatId);
        }
    }
}

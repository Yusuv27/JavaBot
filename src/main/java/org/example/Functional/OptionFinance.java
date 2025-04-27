package org.example.Functional;

import lombok.extern.slf4j.Slf4j;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.example.enumOption.OptionEnum.CREDIT_CALCULATE;
import static org.example.enumOption.OptionEnum.DEPOSIT_CALCULATE;

@Slf4j
public class OptionFinance {
    public static void openMenu(Update update, SendMessage message, long chatId) {
        try {
            String callbackData = update.getCallbackQuery().getData();

            message.setChatId(String.valueOf(chatId));
            Logger.logAcceptActivation(update,chatId);
            if (callbackData.equals(CREDIT_CALCULATE.getName())) {


                message.setText("Введите /creditCalculate Сумма кредита Процент Число месяцев (Через пробел)");
                Logger.logSend(message,chatId);

            } else if (callbackData.equals(DEPOSIT_CALCULATE.getName())) {
                message.setText("Введите сумму вклада, процент и число месяцев (например, /depositCalculate 1000000 5 3).");
                Logger.logSend(message,chatId);
            }
        } catch (Exception e) {
            log.error("Ошибка на стороне OptionFuntionalAll", e);
            e.printStackTrace();
        }
    }
}

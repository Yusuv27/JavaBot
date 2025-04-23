package org.example.Functional;

import lombok.extern.slf4j.Slf4j;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.example.enumOption.OptionEnum.GET_TIME;
import static org.example.enumOption.OptionEnum.UNKNOW;

@Slf4j
public class OptionFuntionalAll {
    public static void joinFuntionalAll(Update update, SendMessage message, long chatId) {
        try {
            String callbackData = update.getCallbackQuery().getData();

            message.setChatId(String.valueOf(chatId));
            Logger.logAcceptActivation(update,chatId);
            if (callbackData.equals(GET_TIME.getName())) {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String formattedTime = now.format(formatter);

                message.setText(formattedTime);
                Logger.logSend(message,chatId);

            } else if (callbackData.equals(UNKNOW.getName())) {
                message.setText("Для данной кнопки нет реализации");
                Logger.logSend(message,chatId);
            }
        } catch (Exception e) {
            log.error("Ошибка на стороне OptionFuntionalAll", e);
            e.printStackTrace();
        }
    }
}

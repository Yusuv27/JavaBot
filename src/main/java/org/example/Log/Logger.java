package org.example.Log;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class Logger {
    public static void logSend(SendMessage message, long chatId) {
        log.info("[Бот]->[" + chatId + "]\n" + message.getText());
    }

    public static void logScheduler(String text) {
        log.info("[Бот]->[SCHEDULER]\n" + text);
    }

    public static void logSend(SendMessage message, long chatId, String text) {
        log.info("[Бот]->[" + chatId + "]\n" + message.getText() + "[" + text + "]");
    }


    public static void logAccept(Update update, long chatId) {
        log.info("[ID отправителя:" + chatId + "] Сообщение:\n" + update.getMessage().getText());
    }

    public static void logAcceptActivation(Update update, long chatId) {
        log.info("[ID отправителя:" + chatId + "] Сообщение:\n" + update.getCallbackQuery().getData());
    }
    public static void logError(Exception e, String nameClass) {
        log.error("[ Class: " + nameClass + "] " + e.getMessage());
    }
}

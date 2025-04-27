package org.example.Keyboard;

import lombok.extern.slf4j.Slf4j;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StartKeyboard {
    public static void startKeybord(SendMessage message,long chatId) {
        message.setText("Я тут!");

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardRow row4 = new KeyboardRow();
        row1.add("Новости\uD83C\uDF0D");
        row1.add("Валюта\uD83D\uDCCA");
        row2.add("Погода☀\uFE0F");
        row2.add("Финансы\uD83D\uDCB0");
        row3.add("Открой сайт");
        keyboard.add(row);
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        replyKeyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(replyKeyboardMarkup);

        Logger.logSend(message, chatId);
    }
}

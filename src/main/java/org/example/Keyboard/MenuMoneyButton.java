package org.example.Keyboard;

import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.example.enumOption.OptionEnum.CREDIT_CALCULATE;
import static org.example.enumOption.OptionEnum.DEPOSIT_CALCULATE;

public class MenuMoneyButton {
    public static void menuOpen(SendMessage message, long chatId) {
        message.setText("\uD83D\uDCB0Финансы\uD83D\uDCB0");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createInlineButton("Посчитать кредит", CREDIT_CALCULATE.getName()));
        rowsInline.add(row1);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(createInlineButton("Посчитать вклад", DEPOSIT_CALCULATE.getName()));
        rowsInline.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowsInline);
        message.setReplyMarkup(inlineKeyboardMarkup);
        Logger.logSend(message, chatId);
    }


    private static InlineKeyboardButton createInlineButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}

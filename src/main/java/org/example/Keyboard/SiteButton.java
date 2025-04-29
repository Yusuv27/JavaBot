package org.example.Keyboard;

import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;

import java.util.ArrayList;
import java.util.List;

public class SiteButton {
    private static String siteURL = "https://iosifproject.ru/marketapp.html";
    private static String sendTextButton = "Нажмите кнопку ниже, чтобы открыть веб-приложение: ";
    private static String nameButton = "Открыть веб-приложение";


    public static void siteButton(SendMessage message, long chatId) {
        message.setText(sendTextButton);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton mySiteButton = new InlineKeyboardButton();
        mySiteButton.setText(nameButton);

        WebAppInfo webAppInfo = new WebAppInfo();
        webAppInfo.setUrl(siteURL);

        mySiteButton.setWebApp(webAppInfo);
        rowInline.add(mySiteButton);
        rowsInline.add(rowInline);

        inlineKeyboardMarkup.setKeyboard(rowsInline);
        message.setReplyMarkup(inlineKeyboardMarkup);
        Logger.logSend(message, chatId, siteURL);
    }
}

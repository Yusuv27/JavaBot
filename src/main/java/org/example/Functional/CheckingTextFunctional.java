package org.example.Functional;

import lombok.extern.slf4j.Slf4j;
import org.example.Functional.FinanceCalculate.CreditCalculate;
import org.example.Functional.FinanceCalculate.DepositCalculate;
import org.example.Keyboard.*;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class CheckingTextFunctional {
    public static void check(Update update, SendMessage message, long chatId) {
        String messageText = update.getMessage().getText();
        chatId = update.getMessage().getChatId();
        Logger.logAccept(update, chatId);

        message.setChatId(String.valueOf(chatId));

        if (messageText.equals("/start")) {
            StartKeyboard.startKeybord(message, chatId);
        } else if (messageText.equalsIgnoreCase("Новости\uD83C\uDF0D")) {
            NewsButton.send(message, chatId);
        } else if (messageText.equalsIgnoreCase("Открой сайт")) {
            SiteButton.siteButton(message, chatId);
        } else if (messageText.equalsIgnoreCase("Меню")) {
            MenuButton.menuButton(message, chatId);
        }else if (messageText.equalsIgnoreCase("Погода☀\uFE0F")){
            WeatherButton.send(message, chatId);
        }else if (messageText.equalsIgnoreCase("Валюта\uD83D\uDCCA")){
            CurrencyButton.send(message, chatId);
        }else if (messageText.equalsIgnoreCase("Финансы\uD83D\uDCB0")){
            MenuMoneyButton.menuOpen(message, chatId);
        }else if(messageText.startsWith("/creditCalculate")) {
            CreditCalculate.result(update,message, chatId);
        } else if (messageText.startsWith("/depositCalculate")) {
            DepositCalculate.result(update,message, chatId);
        }else {
            message.setText("Вы написали:\n ``` \n" + messageText + "\n ``` ");
            message.setParseMode("Markdown");
            Logger.logSend(message,chatId);
        }
    }
}

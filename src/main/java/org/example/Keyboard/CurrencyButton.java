package org.example.Keyboard;

import org.example.Functional.Currency.CurrencyParset;
import org.example.Functional.News.MediaMetricsParser;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CurrencyButton {
    public static void send(SendMessage message, long chatId) {
        String news = CurrencyParset.fetchLinks();
        message.setText(news);
        message.setParseMode("Markdown");
        Logger.logSend(message, chatId);
    }
}

package org.example.Keyboard;

import org.example.Functional.News.MediaMetricsParser;
import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class NewsButton {
    public static void send(SendMessage message, long chatId) {
        String news = MediaMetricsParser.fetchLinks();
        message.setText(news);
        Logger.logSend(message, chatId);
    }
}

package org.example.Keyboard;

import org.example.Functional.Currency.CurrencyParset;
import org.example.Functional.News.MediaMetricsParser;
import org.example.Functional.ToolsString.ToolsString;
import org.example.Log.Logger;
import org.example.ReadFile.ReadFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CurrencyButton {
    public static void send(SendMessage message, long chatId) {
        ToolsString.addFunc(message, ReadFile.readFileCurrency(), chatId);
    }
}

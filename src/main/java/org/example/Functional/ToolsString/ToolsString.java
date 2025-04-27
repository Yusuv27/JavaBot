package org.example.Functional.ToolsString;

import org.example.Log.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ToolsString {
    public static String limitStringLength(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength);
        }
        return str;
    }

    public static void addFunc(SendMessage message, String text, long chatId) {
        message.setText(text);
        message.setParseMode("Markdown");
        Logger.logSend(message, chatId);
    }
}

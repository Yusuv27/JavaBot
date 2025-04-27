package org.example.Keyboard;

import org.example.Functional.ToolsString.ToolsString;
import org.example.Functional.Weather.WeatherParser;
import org.example.ReadFile.ReadFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class WeatherButton {
    public static void send(SendMessage message, long chatId) {
        ToolsString.addFunc(message, ReadFile.readFileWeather(), chatId);
    }
}

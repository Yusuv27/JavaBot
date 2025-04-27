package org.example.Functional.Weather.FilterText;

import java.util.HashMap;
import java.util.Map;

import static org.example.enumOption.ContentSmile.RUSSIASMILE;
import static org.example.enumOption.WeatherSmile.*;

public class FilterTextWeather {
    private static final Map<String, String> replacements = new HashMap<>();

    static {

        replacements.put("пн,", "Пн,");
        replacements.put("вт,", "Вт,");
        replacements.put("ср,", "Ср,");
        replacements.put("чт,", "Чт,");
        replacements.put("пт,", "Пт,");
        replacements.put("сб,", "Сб,");
        replacements.put("вс,", "Вс,");

        replacements.put("Ясно", CLEARWEATHER.getName() + "Ясно");
        replacements.put("Малооблачно", LITTLECLOUDY.getName() + "Малооблачно");
        replacements.put("Облачно", CLOUDY.getName() + "Облачно");
        replacements.put("Снег", SNOW.getName() + "Снег");
    }

    public static String filterWeatherParserText(String text) {

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }
}

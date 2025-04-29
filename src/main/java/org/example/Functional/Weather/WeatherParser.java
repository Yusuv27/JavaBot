package org.example.Functional.Weather;

import org.example.Functional.News.FilterText.FilterTextNews;
import org.example.Functional.ToolsString.ToolsString;
import org.example.Functional.Weather.FilterText.FilterTextWeather;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WeatherParser {
    //private static final String URL = "https://www.gismeteo.ru/weather-moscow-4368/19-day/";
    private static final String URL = "https://www.gismeteo.ru/weather-moscow-4368/";
    private static final String mainPath = "main.content.wrap " +
            "div.content-column " +
            "section.section.section-content " +
            "div.widget.widget-weather-parameters.js-dataset.widget-oneday.widget-today " +
            "div.widget-body " +
            "div.widget-items.js-scroll-item ";
    private static final String valueTemperaturePath = "div.widget-row-chart.widget-row-chart-temperature-air.row-with-caption " +
            "div.chart " +
            "div.values " +
            "temperature-value"; //value

    private static final String valueTimePath = "div.widget-row.widget-row-datetime-time " +
            "div.row-item " +
            "span";
    private static final String valueTypeWeather = "div.widget-row.widget-row-icon " +
            "div.row-item"; //data-tooltip
    private static final String allPathDayToDay = "main.content.wrap " +
            "div.content-column " +
            "section.section.section-content.section-bottom-collapse " +
            "div.weathertabs " +
            "div.weathertab.is-active " +
            "div.weathertab-wrap " +
            "div.date.date-0";


    public static String fetchLinks() {
        StringBuilder weatherText = new StringBuilder();
        try {

            Document doc = Jsoup.connect(URL).get();

            Elements articlesValeuTemperature = doc.select(mainPath + valueTemperaturePath);
            Elements articlesValueTime = doc.select(mainPath + valueTimePath);
            Elements articlesValueType = doc.select(mainPath + valueTypeWeather);

            weatherText.append("*").append(doc.select(allPathDayToDay).text()).append("*\n");

            for(int i = 0; i < articlesValeuTemperature.size(); i ++){
                String temperature;
                try {
                    if (Integer.parseInt(articlesValeuTemperature.get(i).attr("value")) > 0) {
                        temperature = "+" + articlesValeuTemperature.get(i).attr("value");
                    } else {
                        temperature = "-" + articlesValeuTemperature.get(i).attr("value");
                    }
                }  catch (NumberFormatException e) {
                    temperature = articlesValeuTemperature.get(i).attr("value");
                }


                String textLine = "*" + articlesValueTime.get(i).text() + "*\n"
                        + temperature
                        + " "
                        + articlesValueType.get(i).attr("data-tooltip")
                        + "\n\n";
                weatherText.append(textLine);
            }


        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при получении данных: " + e.getMessage();
        }


        String resultText = "Источник: Gismeteo\n\n" + FilterTextNews.filterMediaMetricsParser(weatherText.toString());

        resultText = ToolsString.limitStringLength(resultText, 3000);

        resultText = FilterTextWeather.filterWeatherParserText(resultText);
        ToolsString.limitStringLength(resultText, 3000);
        return resultText;
    }
}

package org.example.Functional.Currency;

import org.example.Functional.News.FilterText.FilterText;
import org.example.Functional.ToolsString.ToolsString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static org.example.enumOption.ContentSmile.*;

public class CurrencyParset {

    private static final String URL = "https://cbr.ru/";

    public static String fetchLinks() {
        StringBuilder newsLinks = new StringBuilder();
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements articles = doc.select("main.home-content div.offsetMenu div.container-fluid div.col-md-23.offset-md-1 div.home-main div.home-main_aside div.home-main_sticky div.main-indicator div.main-indicator_value");
            int counter = 0;
            for (Element article : articles) {
                String linkText = article.text();

                counter++;
                if (counter == 1) {
                    newsLinks.append("*Цель по инфляции: *").append(linkText).append("\n");
                } else if (counter == 2) {
                    newsLinks.append("*Инфляция: *").append(linkText).append("\n");
                } else if (counter == 3) {
                    newsLinks.append("*Ключевая ставка: *").append(linkText).append("\n\n");
                }
            }

            Elements intRubYestoday = doc.select("main.home-content div.offsetMenu div.container-fluid div.col-md-23.offset-md-1 div.home-main div.home-main_aside div.home-main_sticky div.main-indicator_rates div.main-indicator_rates-table div.main-indicator_rate div.col-md-2.col-xs-9._right.mono-num");
            counter = 0;
        for(Element article : intRubYestoday) {
            String linkText = article.text();

            counter++;
            if (counter == 1) {
                newsLinks.append(CHINASMILE.getName() + "*Юань (Вчера): *").append(linkText).append("\n");
            } else if (counter == 2) {
                newsLinks.append(CHINASMILE.getName() + "*Юань (Сегодня): *").append(linkText).append("\n\n");
            } else if (counter == 3) {
                newsLinks.append(USASMILE.getName() + "*Доллар (Вчера): *").append(linkText).append("\n");
            } else if (counter == 4) {
                newsLinks.append(USASMILE.getName() + "*Доллар (Сегодня): *").append(linkText).append("\n\n");
            } else if (counter == 5) {
                newsLinks.append(EUROSMILE.getName() + "*Евро (Вчера): *").append(linkText).append("\n");
            } else if (counter == 6) {
                newsLinks.append(EUROSMILE.getName() + "*Евро (Сегодня): *").append(linkText).append("\n");
            }
        }

        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при получении данных: " + e.getMessage();
        }
        String resultText = "Источник: cbr\n\n" + FilterText.filterMediaMetricsParser(newsLinks.toString());

        resultText = ToolsString.limitStringLength(resultText, 3000);

        return resultText;
    }
}

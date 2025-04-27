package org.example.Functional.Currency;

import org.example.Functional.News.FilterText.FilterTextNews;
import org.example.Functional.ToolsString.ToolsString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static org.example.enumOption.ContentSmile.*;

public class CurrencyParset {

    private static final String URL = "https://cbr.ru/";
    private static final String tagMain = "main.home-content " +
            "div.offsetMenu " +
            "div.container-fluid " +
            "div.col-md-23.offset-md-1 " +
            "div.home-main " +
            "div.home-main_aside " +
            "div.home-main_sticky " +
            "div.main-indicator " +
            "div.main-indicator_value";
    private static final String tagIntRubYestoday = "main.home-content " +
            "div.offsetMenu " +
            "div.container-fluid " +
            "div.col-md-23.offset-md-1 " +
            "div.home-main " +
            "div.home-main_aside " +
            "div.home-main_sticky " +
            "div.main-indicator_rates " +
            "div.main-indicator_rates-table " +
            "div.main-indicator_rate " +
            "div.col-md-2.col-xs-9._right.mono-num";

    private static final String inflationTarget = "*Цель по инфляции: *";
    private static final String Inflation = "*Инфляция: *";
    private static final String keyBid = "*Ключевая ставка: *";


    private static final String currencyChineYestodayText = "*Юань (Вчера): *";
    private static final String currencyChineTodayText = "*Юань (Сегодня): *";
    private static final String currencyUSAYestodayText = "*Доллар (Вчера): *";
    private static final String currencyUSATodayText = "*Доллар (Сегодня): *";
    private static final String currencyEUROYestodayText = "*Евро (Вчера): *";
    private static final String currencyEUROTodayText = "*Евро (Сегодня): *";
    private static final String resource = "Источник: cbr\n\n";



    public static String fetchLinks() {
        StringBuilder newsLinks = new StringBuilder();
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements articles = doc.select(tagMain);
            int counter = 0;
            for (Element article : articles) {
                String linkText = article.text();

                counter++;
                switch (counter) {
                    case 1:
                        newsLinks.append(inflationTarget).append(linkText).append("\n");
                        break;
                    case 2:
                        newsLinks.append(Inflation).append(linkText).append("\n");
                        break;
                    case 3:
                        newsLinks.append(keyBid).append(linkText).append("\n\n");
                        break;
                }
            }

            Elements intRubYestoday = doc.select(tagIntRubYestoday);
            counter = 0;
        for(Element article : intRubYestoday) {
            String linkText = article.text();

            counter++;
            switch (counter) {
                case 1:
                    newsLinks.append(CHINASMILE.getName() + currencyChineYestodayText).append(linkText).append("\n");
                    break;
                case 2:
                    newsLinks.append(CHINASMILE.getName() + currencyChineTodayText).append(linkText).append("\n\n");
                    break;
                case 3:
                    newsLinks.append(USASMILE.getName() + currencyUSAYestodayText).append(linkText).append("\n");
                    break;
                case 4:
                    newsLinks.append(USASMILE.getName() + currencyUSATodayText).append(linkText).append("\n\n");
                    break;
                case 5:
                    newsLinks.append(EUROSMILE.getName() + currencyEUROYestodayText).append(linkText).append("\n");
                    break;
                case 6:
                    newsLinks.append(EUROSMILE.getName() + currencyEUROTodayText).append(linkText).append("\n");
                    break;
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при получении данных: " + e.getMessage();
        }
        String resultText = resource + FilterTextNews.filterMediaMetricsParser(newsLinks.toString());

        resultText = ToolsString.limitStringLength(resultText, 3000);

        return resultText;
    }
}

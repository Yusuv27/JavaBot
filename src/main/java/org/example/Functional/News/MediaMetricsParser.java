package org.example.Functional.News;

import org.example.Functional.News.FilterText.FilterTextNews;
import org.example.Functional.ToolsString.ToolsString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MediaMetricsParser {

    private static final String URL = "https://www.kommersant.ru/rubRIc/5";
    private static final String main = "main " +
            "div.layout " +
            "div.rubric " +
            "section.main.grid " +
            "div.rubric_lenta " +
            "article.uho.rubric_lenta__item.js-article";
    private static final String resource = "Источник: Лента, газета Коммерсант\n\n";


    public static String fetchLinks() {
        StringBuilder newsLinks = new StringBuilder();
        try {
            Document doc = Jsoup.connect(URL).get();

            Elements articles = doc.select(main);


            for (Element article : articles) {

                String linkText = article.select("h2.uho__name.rubric_lenta__item_name").text();
                String timeElementPath = "p.uho__tag.rubric_lenta__item_tag.hide_desktop";
                String timeText = article.select(timeElementPath).text();

                String lineNews = "*" + timeText + "*\n" + linkText + "\n\n";
                newsLinks.insert(0, lineNews);
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

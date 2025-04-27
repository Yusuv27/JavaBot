package org.example.Functional.News;

import org.example.Functional.News.FilterText.FilterText;
import org.example.Functional.ToolsString.ToolsString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MediaMetricsParser {

    private static final String URL = "https://www.kommersant.ru/rubRIc/5";

    public static String fetchLinks() {
        StringBuilder newsLinks = new StringBuilder();
        try {
            // Отправляем GET-запрос и получаем HTML-документ
            Document doc = Jsoup.connect(URL).get();

            Elements articles = doc.select("main div.layout div.rubric section.main.grid div.rubric_lenta article.uho.rubric_lenta__item.js-article");

            // Проходим по всем найденным элементам <article>
            for (Element article : articles) {
                // Получаем текст ссылки
                String linkText = article.select("h2.uho__name.rubric_lenta__item_name").text();

                // Здесь указываем путь к элементу времени
                String timeElementPath = "p.uho__tag.rubric_lenta__item_tag.hide_desktop";

                // Получаем время публикации
                String timeText = article.select(timeElementPath).text();

                // Формируем вывод с временем и ссылкой
                String lineNews = "*" + timeText + "*\n" + linkText + "\n\n";
                newsLinks.insert(0, lineNews);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при получении данных: " + e.getMessage();
        }
        String resultText = "Источник: Лента, газета Коммерсант\n\n" + FilterText.filterMediaMetricsParser(newsLinks.toString());

        resultText = ToolsString.limitStringLength(resultText, 3000);

        return resultText;
    }
}

package org.example.Functional.News;

import org.example.Functional.News.FilterText.FilterText;
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

            // Используем селектор для поиска всех <a> внутри .rs-link,
            // находящихся внутри нужной секции
            Elements links = doc.select("main div.layout div.rubric section.main.grid div.rubric_lenta article.uho.rubric_lenta__item.js-article div.uho__text.rubric_lenta__item_text h2.uho__name.rubric_lenta__item_name");
//            System.out.printf(links.toString());

            // Проходим по всем найденным элементам <a>
            for (Element link : links) {
                String linkText = link.text(); // Получаем текст ссылки
                String linkHref = link.attr("href"); // Получаем адрес ссылки
                newsLinks.append(linkText).append(" - ").append(linkHref).append("\n"); // Формируем вывод
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Ошибка при получении данных: " + e.getMessage();
        }
        String resultText = "Источник: Лента, газета Коммерсант\n\n" + FilterText.filterMediaMetricsParser(newsLinks.toString());

        resultText = limitStringLength(resultText, 3000);

        return resultText;
    }

    public static String limitStringLength(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength); // Добавление "..." для обозначения, что строка была обрезана
        }
        return str;
    }
}

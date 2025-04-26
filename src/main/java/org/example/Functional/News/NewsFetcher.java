package org.example.Functional.News;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.example.Functional.News.FilterText.FilterText;
import org.json.JSONArray;
import org.json.JSONObject;

public class NewsFetcher {
    private final String apiKey = "8b1384b6867844deabdbf61a6f5b1144"; // Вставьте здесь ваш API-ключ
    private final String apiUrl = "https://newsapi.org/v2/top-headlines?country=us&apiKey=";

    public String fetchNews() {
        StringBuilder newsString = new StringBuilder();
        try {
            // Создаем URL для запроса
            String urlString = apiUrl + apiKey;
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Проверяем код ответа
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                // Читаем ответ
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Обрабатываем JSON-ответ
                newsString.append(parseJson(response.toString()));
            } else {
                return "Ошибка: " + conn.getResponseCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Произошла ошибка: " + e.getMessage();
        }
        return newsString.toString();
    }

    private String parseJson(String jsonResponse) {
        StringBuilder news = new StringBuilder();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray articles = jsonObject.getJSONArray("articles");

        news.append("Today's News:\n");
        for (int i = 0; i < articles.length(); i++) {
            JSONObject article = articles.getJSONObject(i);
            String title = article.getString("title");
            String description = article.getString("description");
            news.append((i + 1) + ". " + title + "\n");
            news.append("   " + description + "\n\n");
        }

        return news.toString();
    }
}

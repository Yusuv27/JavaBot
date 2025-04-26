package org.example.Functional.Translate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Translate {
    private static final String API_URL = "https://api-free.deepl.com/v2/translate";

    public static String translate(String text, String apiKey) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "DeepL-Auth-Key " + apiKey);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        String params = "text=" + text + "&target_lang=RU"; // Установите целевой язык

        try (OutputStream os = connection.getOutputStream()) {
            os.write(params.getBytes());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        // Парсинг ответа
        return parseTranslation(response.toString());
    }

    private static String parseTranslation(String jsonResponse) {
        // Простой парсинг ответа для извлечения переведенного текста
        int start = jsonResponse.indexOf("\"translatedText\":\"") + 18;
        int end = jsonResponse.indexOf("\"", start);
        return jsonResponse.substring(start, end);
    }
}

package org.example.ReadFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.example.enumOption.FilePath.*;


public class ReadFile {
    public static String readFileWeather() {
        StringBuilder text = new StringBuilder(); // Используем StringBuilder для накопления строк
        try (BufferedReader reader = new BufferedReader(new FileReader(WEATHERTXTPATH.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(System.lineSeparator()); // Добавляем строки с переходом на новую строку
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return text.toString();
    }
    public static String readFileNews() {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(NEWSTXTPATH.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return text.toString();
    }
    public static String readFileCurrency() {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(CURRENCYTXTPATH.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return text.toString();
    }
}

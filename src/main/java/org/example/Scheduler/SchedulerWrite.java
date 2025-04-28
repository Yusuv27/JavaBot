package org.example.Scheduler;

import org.example.Functional.Currency.CurrencyParset;
import org.example.Functional.News.MediaMetricsParser;
import org.example.Functional.Weather.WeatherParser;
import org.example.Log.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.example.enumOption.FilePath.*;

public class SchedulerWrite implements Runnable {

    @Override
    public void run() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                writeToFile(WeatherParser.fetchLinks(), WEATHERTXTPATH.getPath(), "Запись weather");

            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.HOURS);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                writeToFile(MediaMetricsParser.fetchLinks(), NEWSTXTPATH.getPath(), "Запись news");
                writeToFile(CurrencyParset.fetchLinks(), CURRENCYTXTPATH.getPath(), "Запись currency");
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    private void writeToFile(String text, String path, String log) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(text);
            Logger.logScheduler(log);
        }
    }
}
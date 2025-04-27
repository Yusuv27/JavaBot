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
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            try {
                writeToFile(WeatherParser.fetchLinks(),
                        MediaMetricsParser.fetchLinks(),
                        CurrencyParset.fetchLinks());
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.HOURS);
    }

    private void writeToFile(String wearther, String news, String currency) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(WEATHERTXTPATH.getPath()))) {
            writer.write(wearther);
            Logger.logScheduler("Запись weather");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(NEWSTXTPATH.getPath()))){
            writer.write(news);
            Logger.logScheduler("Запись news");
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CURRENCYTXTPATH.getPath()))) {
            writer.write(currency);
            Logger.logScheduler("Запись currency");
        }
    }
}
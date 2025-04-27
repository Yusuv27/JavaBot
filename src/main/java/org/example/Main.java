package org.example;

import org.example.Scheduler.SchedulerWrite;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi  botsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            botsApi.registerBot(new EchoBot());

            SchedulerWrite writer = new SchedulerWrite();
            Thread writerThread = new Thread(writer);
            writerThread.start();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

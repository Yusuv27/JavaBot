package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.Functional.CheckingTextFunctional;
import org.example.Functional.OptionFinance;
import org.example.Functional.OptionFuntionalAll;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Slf4j
public class EchoBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        long chatId;
        SendMessage message = new SendMessage();

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            OptionFuntionalAll.joinFuntionalAll(update,message,chatId);
            OptionFinance.openMenu(update,message,chatId);

        } else if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();

            CheckingTextFunctional.check(update,message,chatId);

        }
        if (message.getText() != null && !message.getText().isEmpty()) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error("Error occurred while sending message", e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "JavaCoffee"; 
    }

    @Override
    public String getBotToken() {
        return "7561474100:AAE0ElzuOEVOnjjI9pl1R61Ig6lFmFMgt8s"; // main
//          return "8044252151:AAE7telpertmz4iPyCf7D6mjuvR_tIRRTWk"; // test
    }
}

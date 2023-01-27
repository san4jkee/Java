import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {

    public static final String BOT_TOKEN = "5683376723:AAH6xIgFgWZeLu7mNFOzDign7Od_b47ZR0s";
    public static final String BOT_USERNAME = "san4jkeeGPT_bot";

    public MyTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }


    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Thread thread1 = new Thread(() -> {
            if (update.hasMessage() && update.getMessage().hasText()) {
                long chatId = update.getMessage().getChatId();
                String messageText = update.getMessage().getText();

                try {
                    String response = ChatGptBot.generateResponse(messageText, 2048);
                    SendMessage messageToSend = new SendMessage(Long.toString(chatId), response);
                    execute(messageToSend);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }});
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

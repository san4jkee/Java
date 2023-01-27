import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import retrofit2.HttpException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TelegramApiException, IOException {
        try {
            new MyTelegramBot();
        }catch (HttpException e) {
            if (e.code() == 400) {
                e.response().errorBody().string();
            }
        }

    }
}
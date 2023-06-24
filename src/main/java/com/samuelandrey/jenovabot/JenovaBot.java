package com.samuelandrey.jenovabot;
import com.samuelandrey.jenovabot.token.TokenApi;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.telegram.telegrambots.bots.*;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class JenovaBot extends TelegramLongPollingBot {
    ConnectionMysql connection;
    TokenApi token = new TokenApi();
    
    public JenovaBot() {  
        try {
            db_bot = new ConnectionMysql();
            run();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
   
 
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            
            String messageText = update.getMessage().getText();
            
            long chatId = update.getMessage().getChatId();
            
            User user = update.getMessage().getFrom();
            long userId = user.getId();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String username = user.getUserName();
            String languageCode = user.getLanguageCode();
            
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            
            String command = messageText.split(" ")[0];
            String parameter = messageText.substring(command.length()).trim();
            
            if (command.equals("/gpt")) {
                
                try {
                    sendResponse(chatId, new ChatGPT().gpt(parameter));
                } catch (IOException ex) {
                    Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String response = connection.getMessageByKeyword(messageText);
                sendResponse(update.getMessage().getChatId(), response);
            }
            

        }
    }
    
    private void sendResponse(Long chatId, String response) {
        SendMessage message = new SendMessage(Long.toString(chatId), response);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "JenovaOneBot";
    }

    @Override
    public String getBotToken() {
        return token.getTokenTelegram();
    }
    
    public void run() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

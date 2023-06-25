package com.samuelandrey.jenovabot;
import com.samuelandrey.jenovabot.token.TokenApi;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            connection = new ConnectionMysql();
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
            
            validateUser(userId, username, firstName, lastName);
            
            // Response chat
            if (command.equals("/gpt") && !parameter.equals("")) {
                
                if (decrementTokenGpt(userId) <= 0) {
                    sendResponse(update.getMessage().getChatId(), "Token Chat GPT sudah habis.");
                } else {
                    try {
                        sendResponse(chatId, new ChatGPT().gpt(parameter));
                    } catch (IOException ex) {
                        Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                String response = getMessageByKeyword(messageText);
                sendResponse(update.getMessage().getChatId(), response);
            }
        }
    }
    
    private void sendResponse(Long chatId, String response) {
        SendMessage message = new SendMessage(Long.toString(chatId), response);
        message.enableMarkdown(true);
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
    
    private void validateUser(long idUser, String username, String firstName, String lastName) {
        PreparedStatement statement = null;

        try {
            // Mengambil data pengguna berdasarkan ID
            String selectQuery = "SELECT * FROM tb_user WHERE id_user = ?";
            statement = connection.getConnection().prepareStatement(selectQuery);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Jika pengguna sudah ada dalam database, periksa apakah ada perubahan dalam data pengguna
                String firstNameDB = resultSet.getString("firstname");
                String lastNameDB = resultSet.getString("lastname");
                String usernameDB = resultSet.getString("username");
                
                if (!firstName.equals(firstNameDB) || !lastName.equals(lastNameDB) || !username.equals(usernameDB)) {
                    // Update data pengguna
                    String updateQuery = "UPDATE tb_user SET firstname = ?, lastname = ?, username = ? WHERE id_user = ?";
                    statement =connection.getConnection().prepareStatement(updateQuery);
                    statement.setString(1, firstName);
                    statement.setString(2, lastName);
                    statement.setString(3, username);
                    statement.setLong(4, idUser);
                    statement.executeUpdate();
                }
            } else {
                // Jika pengguna baru, tambahkan data pengguna ke database
                String insertQuery = "INSERT INTO tb_user (id_user, firstname, lastname, username) VALUES (?, ?, ?, ?)";
                statement = connection.getConnection().prepareStatement(insertQuery);
                statement.setLong(1, idUser);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, username);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int decrementTokenGpt(long idUser) {
        PreparedStatement statement = null;
        int token = 0;
        try {
            // Mengambil nilai token setelah diubah
            String selectQuery = "SELECT * FROM tb_user WHERE id_user = ?";
            statement = connection.getConnection().prepareStatement(selectQuery);
            statement.setLong(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                token = resultSet.getInt("token");
            }
            
            if (token > 0) {
                // Update nilai token dengan melakukan decrement
                String updateQuery = "UPDATE tb_user SET token = token - 1 WHERE id_user = ?";
                statement = connection.getConnection().prepareStatement(updateQuery);
                statement.setLong(1, idUser);
                statement.executeUpdate();
            } else {
                token = 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }

        return token;
    }
    
    public String getMessageByKeyword(String command) {
    
        PreparedStatement statement = null;
        String message = null;

        try {
            // Mengambil nilai token setelah diubah
            String selectQuery = "SELECT * FROM tb_keyword WHERE command = ?";
            statement = connection.getConnection().prepareStatement(selectQuery);
            statement.setString(1, command);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                message = resultSet.getString("response");
            } else {
                
                selectQuery = "SELECT * FROM tb_keyword WHERE command = ?";
                statement.setString(1, "/setdefault");
                resultSet = statement.executeQuery();
                
                if (resultSet.next()) {
                    message = resultSet.getString("response");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }

        return message;
    }
    
    public void sendBroadcastMessage(String message) {
        
        long chatId = 0;
        PreparedStatement statement = null;
        try {
            String selectQuery = "SELECT * FROM tb_broadcast_info";
            statement = connection.getConnection().prepareStatement(selectQuery);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                chatId = resultSet.getLong("id_user");
                sendResponse(chatId, message);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JenovaBot.class.getName()).log(Level.SEVERE, null, ex);
        }

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

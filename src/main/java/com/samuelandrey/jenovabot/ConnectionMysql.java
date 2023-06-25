package com.samuelandrey.jenovabot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMysql {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jenova_bot";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Connection connection;

    public Connection getConnection(){
        return connection;
    }
    
    public ConnectionMysql() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Berhasil terhubung ke database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMessageByKeyword(String command) {
        String message = null;
        String unknownResponse = "Jenova Bot\n\nBerikut adalah command yang tersedia :\nChat GPT\n/gpt 'spasi' prompt";
        String sql = "SELECT * FROM tb_keyword WHERE command = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, command);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    message = resultSet.getString("response");
                } else {
                    message = unknownResponse;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return message;
    }
    
}

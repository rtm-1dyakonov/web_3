package com.connection;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnector {
    // строка подключения к бд SQLite
    private static final String url = "jdbc:sqlite:C:\\Users\\ra_a9\\Desktop\\Task5\\Rental.db";

    // метод для получения соединения с бд
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url); // получение соединения
        } catch (SQLException e) { // если соединение не удалось установить
            System.out.println(e.getMessage()); // печать содержимого ошибки
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLiteConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn; // возвращение переменной для использования соединения
    }
}

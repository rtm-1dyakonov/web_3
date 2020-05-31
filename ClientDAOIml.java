package com.com.dao;

import com.connection.SQLiteConnector;
import com.models.*;

import java.sql.*;
import java.util.ArrayList;

public class ClientDAOIml implements ClientDAO {
    private final SQLiteConnector sqlConnector = new SQLiteConnector();
    @Override
    public void setupClient(Client client) {
        String sqlRental = "Select ClientRental.ClientId, Rental.RentalId, Point from Rental\n" +
                "Inner Join ClientRental\n" +
                "On Rental.RentalId = ClientRental.RentalId \n" +
                "Where ClientRental.ClientId in (select ClientId from Client where Login=? " +
                "and Password=?)";

        String sqlCar = "Select * from Car Where RentalId = ?";
        String sqlClientName = "Select Name from Client where Login=? and Password=?";

        String login = client.getLogin();
        String password = client.getPassword();
        ResultSet rs = null, rs2 = null;
        Connection conn = null;
        PreparedStatement pstmt0 = null,pstmt1 = null, pstmt2 = null;

        try {
            // соединение с бд
            conn = this.sqlConnector.connect();
            // установка режима auto-commit в false
            conn.setAutoCommit(false);
            pstmt0 = conn.prepareStatement(sqlClientName);
            pstmt1 = conn.prepareStatement(sqlRental);
            pstmt2 = conn.prepareStatement(sqlCar);

            pstmt0.setString(1,login);
            pstmt0.setString(2,password);

            rs=pstmt0.executeQuery();
            while(rs.next()){
                client.setName(rs.getString("Name"));
            }
            // установка значения первого параметра
            pstmt1.setString(1, login);
            pstmt1.setString(2, password);

            // получение результат выполнения запроса
            rs = pstmt1.executeQuery();
            int i = 0;
            // проход по строкам результата выполнения запроса
            client.setList(new ArrayList<>());
            while (rs.next()) {
                // добавление списка в обьект класса Client
                client.addListItem(new Rental(rs.getString("Point")));
                client.getListItem(i).setId(rs.getInt("RentalId"));
                // установка значения первого параметра
                pstmt2.setInt(1, client.getListItem(i).getId());
                rs2 = pstmt2.executeQuery();
                // проход по строкам результата выполнения запроса
                int j = 0;

                while (rs2.next()) { // чтения строки результата выполнения запроса
                    // добавление автомобиля в список
                    client.getListItem(i).add(new Car(rs2.getString("Model"),
                            rs2.getString("Manufacturer"),
                            rs2.getDouble("Price"), rs2.getInt("Count")));
                    client.getListItem(i).getCar(j++).setId(rs2.getInt("CarId"));
                }

                i++;
            }

            // подтверждение изменений, внесенных транзакцией
            conn.commit();
        } catch (SQLException e1) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
            System.out.println(e1.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (pstmt0 != null) {
                    pstmt0.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e3) {
                System.out.println(e3.getMessage());
            }
        }

    }

    @Override
    public boolean isClientExist(String login, String password) {
        String sql = "Select ClientId from Client \n" +
                "Where Login = ? AND Password = ?";
        boolean result = false;
        try (Connection conn = this.sqlConnector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            // получение результата выполнения запроса
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) { // если в ответе есть строка значит существует
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}

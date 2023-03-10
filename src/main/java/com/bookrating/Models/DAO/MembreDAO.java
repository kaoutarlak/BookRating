package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.membre;

import java.sql.*;
import java.util.List;

public class MembreDAO implements IMembreDAO{
    public static final String URL = "jdbc:mysql://mysql-kaoutarlak.alwaysdata.net:3306/kaoutarlak_bookrating";
    public static final String USERNAME = "290054_admin";
    public static final String PASSWORD = "Admin@2022.";
    Connection connection = null;


    @Override
    public void establichConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public membre addMembre(membre membre) {
        return null;
    }

    @Override
    public membre login(String login, String password) {
        return null;
    }

    @Override
    public List<membre> allUtilisateur() {
        return null;
    }


}

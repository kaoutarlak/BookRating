package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.categorieEvaluation;
import com.bookrating.Models.Entities.evaluation;
import com.bookrating.Models.Entities.signalement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignalementDAO implements ISignalementDAO {
    public static final String URL = "jdbc:mysql://mysql-kaoutarlak.alwaysdata.net:3306/kaoutarlak_bookrating";
    public static final String USERNAME = "290054_admin";
    public static final String PASSWORD = "Admin@2022";
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
    public void addSignal(signalement newSignalement) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `signalement`(`message`,`login`, `idAvis`) VALUES(?,?,?); ");
            ps.setString(1, newSignalement.getMessage());
            ps.setString(2, newSignalement.getLogin());
            ps.setInt(3, newSignalement.getIdAvis());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }


}

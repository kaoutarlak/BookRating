package com.bookrating.Models.DAO;

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

    @Override
    public List<signalement> getAllSignal() {
        List<signalement> signalementList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `signalement`;");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                signalement s = new signalement();
                s.setId(resultSet.getInt("id"));
                s.setMessage(resultSet.getString("message"));
                s.setVerifier(resultSet.getInt("verifier"));
                s.setLogin(resultSet.getString("login"));
                s.setIdAvis(resultSet.getInt("idAvis"));
                signalementList.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return signalementList;
    }

    @Override
    public void verifierSignalement(int idSignal) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `signalement` SET verifier=1 WHERE id=?");
            ps.setInt(1,idSignal);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }


}

package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.membre;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MembreDAO implements IMembreDAO {
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
    public membre addMembre(membre membre) {
        String login = membre.getLogin();
        String password = membre.getPassword();
        String nom = membre.getNom();
        String prenom = membre.getPrenom();
        String telephone = membre.getTelephone();
        String adresse = membre.getAdresse();
        LocalDate dateNaissance = membre.getDateNaissance();
        LocalDate dateInscription = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateInscriptionString = dateInscription.format(formatter);
        String dateNaissanceString = (dateNaissance != null) ? dateNaissance.format(formatter) : "";
        int active = 1;
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO `membre`(`login`, `password`, `nom`, `prenom`, `telephone`, `adresse`, `dateNaissance`, `dateInsscription`, `active`) " +
                    " VALUES ('" + login + "','" + password + "','" + nom + "','" + prenom + "','" + telephone + "','" + adresse + "','" + dateNaissanceString + "','" + dateInscriptionString + "'," + active + ")");
            closeConnection();
            return membre;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExistLogin(String login) {
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT login FROM `membre` WHERE login = '" + login + "'");

            if (rs.next()) {
                closeConnection();
                return true;
            } else {
                closeConnection();
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public membre login(String login, String password) {
        membre membreConnect = new membre();
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `membre` WHERE login = '" + login + "' AND password= '" + password + "'");

            while (rs.next()) {
                membreConnect.setLogin(rs.getString("login"));
                membreConnect.setPassword(rs.getString("password"));
                membreConnect.setNom(rs.getString("nom"));
                membreConnect.setPrenom(rs.getString("prenom"));
                membreConnect.setTelephone(rs.getString("telephone"));
                membreConnect.setAdresse(rs.getString("adresse"));
                membreConnect.setPhoto(rs.getString("photo"));
                membreConnect.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                membreConnect.setDateInsscription(rs.getDate("dateInsscription").toLocalDate());
                membreConnect.setActive(rs.getByte("active"));
            }
            closeConnection();
            return membreConnect;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<membre> allUtilisateur() {
        return null;
    }


}

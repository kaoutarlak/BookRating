package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.demandeGestion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeGestionDAO implements IDemandeGestionDAO {

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
    public List<demandeGestion> getAllDemande() {
        List<demandeGestion> demandeGestionList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `demandeGestion`;");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                demandeGestion demande = new demandeGestion();
                demande.setId(resultSet.getInt("id"));
                demande.setMessage(resultSet.getString("message"));
                demande.setEtatDemande(resultSet.getString("etatDemande"));
                demande.setLoginAuteur(resultSet.getString("loginAuteur"));
                demande.setLoginAdmin(resultSet.getString("loginAdmin"));
                demande.setIdLivre(resultSet.getInt("idLivre"));

                demandeGestionList.add(demande);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return demandeGestionList;
    }

    @Override
    public void faireDemande(demandeGestion demande) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `demandeGestion`(`message`," +
                    " `etatDemande`, `loginAuteur`, `idLivre`) VALUES(?,?,?,?); ");
            ps.setString(1, demande.getMessage());
            ps.setString(2, demande.getEtatDemande());
            ps.setString(3, demande.getLoginAuteur());
            ps.setInt(4, demande.getIdLivre());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void repondreDemande(int idDemande, String etatDemande, String Admin) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `demandeGestion` SET `etatDemande`=? ,`loginAdmin`=? WHERE `id`=?");
            ps.setString(1, etatDemande);
            ps.setString(2, Admin);
            ps.setInt(3, idDemande);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

}

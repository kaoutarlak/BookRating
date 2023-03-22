package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.auteur;
import com.bookrating.Models.Entities.categorieLivre;
import com.bookrating.Models.Entities.livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuteurDAO implements IAuteurDAO {
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
    public void addAuteur(auteur newAuteur) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `auteur`(`code`, `login`) VALUES(?,?); ");
            ps.setString(1, newAuteur.getCode());
            ps.setString(2, newAuteur.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<livre> getlivreAddByAuteur(String auteur) {
        List<livre> livres = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `livre` WHERE addBy=?;");
            ps.setString(1, auteur);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                livre l = new livre();
                l.setId(rs.getInt("id"));
                l.setIdCategorieLivre(rs.getInt("idCategorieLivre"));
                l.setTitre(rs.getString("titre"));
                l.setNomAuteur(rs.getString("nomAuteur"));
                l.setDateParution(rs.getDate("dateParution").toLocalDate());
                l.setImage(rs.getString("image"));
                l.setDescription(rs.getString("description"));
                l.setAddBy(rs.getString("addBy"));
                livres.add(l);
            }
            return livres;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

}

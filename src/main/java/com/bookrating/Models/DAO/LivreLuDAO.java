package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.livre;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivreLuDAO implements ILivreLuDAO {
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
    public void addLivreLu(String login, int idLivre) {
        try {
            establichConnection();
            Statement st = connection.createStatement();
            ResultSet resultSet= st.executeQuery("SELECT * FROM `livreLu` WHERE `login`='"+login+"' AND `idLivre`="+idLivre);
            if (resultSet.next()){
                closeConnection();
            }else {
                st.executeUpdate("INSERT INTO `livreLu`(`login`, `idLivre`) VALUES ('" + login + "'," + idLivre + ")");
                closeConnection();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<livre> getAllLivreLu(String login) {
        try {
            establichConnection();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM `livreLu` JOIN `livre` ON `livreLu`.`idLivre` = `livre`.`id` WHERE login= '" + login + "'");
            List<livre> livreLuList  = new ArrayList<>();

            while (result.next()) {
                livre l = new livre();

                l.setId(result.getInt("livre.id"));
                l.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setDateParution(LocalDate.parse(result.getString("dateParution")));
                l.setImage(result.getString("image"));
                l.setDescription(result.getString("description"));

                livreLuList.add(l);
            }

            closeConnection();
            return livreLuList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int nbPageLivreLu(String login) {
        int nbPageLivre = 0;
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ROUND(COUNT(*) / 10) FROM `livreLu` WHERE login = '" + login + "'");

            if (rs.next()) {
                nbPageLivre = rs.getInt(1);
            }

            closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbPageLivre;
    }
}

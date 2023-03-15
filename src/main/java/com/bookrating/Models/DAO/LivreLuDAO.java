package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.categorieLivre;

import java.sql.*;
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
    public List<categorieLivre> getAllLivreLu() {
        return null;
    }
}

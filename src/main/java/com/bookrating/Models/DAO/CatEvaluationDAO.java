package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.categorieEvaluation;
import com.bookrating.Models.Entities.categorieLivre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatEvaluationDAO implements ICatEvaluationDAO {
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
    public void addCategorieEvaluation(categorieEvaluation categorieEvaluation) {
        try {
            establichConnection();
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO `categorieEvaluation`(`description`) VALUES ('"+categorieEvaluation.getDescription()+"')");

            closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void alterCategorieEvaluation(categorieEvaluation categorieEvaluation) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `categorieEvaluation` SET `description`=? WHERE `id`=?");
            ps.setString(1, categorieEvaluation.getDescription());
            ps.setInt(2, categorieEvaluation.getId());
            ps.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<categorieEvaluation> getAllCategoriesEvaluation() {
        try {
            establichConnection();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM `categorieEvaluation`");
            List<categorieEvaluation> categorieEvaluations  = new ArrayList<>();

            while (result.next()) {
                categorieEvaluation c = new categorieEvaluation();
                c.setId(result.getInt("id"));
                c.setDescription(result.getString("description"));
                categorieEvaluations.add(c);
            }

            closeConnection();
            return categorieEvaluations;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

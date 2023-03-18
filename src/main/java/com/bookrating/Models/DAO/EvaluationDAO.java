package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EvaluationDAO implements IEvaluationDAO {
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
    public void addAvis(avis newAvis) {

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);

        try {
            establichConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `avis`(`datePost`, `commentaire`, `nbLikes`, `login`, `idLivre`) " +
                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, dateString);
            preparedStatement.setString(2, newAvis.getCommentaire());
            preparedStatement.setInt(3, 0);
            preparedStatement.setString(4, newAvis.getLogin());
            preparedStatement.setInt(5, newAvis.getIdLivre());

            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addEvaluationByAvis(evaluation newEvaluation) {
        try {
            establichConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `evaluation`(`note`, `idCategorieEvaluation`, `idAvis`) " +
                    "VALUES (?,?,?)");
            preparedStatement.setInt(1, newEvaluation.getNote());
            preparedStatement.setInt(2, newEvaluation.getIdCategorieEvaluation());
            preparedStatement.setInt(3, newEvaluation.getIdAvis());
            preparedStatement.executeUpdate();
            closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<evaluation> getAllEvaluationByMember(String login) {
        return null;
    }

    @Override
    public List<evaluation> getAllEvaluationByLivre(int idLivre) {
        return null;
    }

    @Override
    public List<evaluation> getAllEvaluation() {
        return null;
    }

    @Override
    public boolean areadyEvaluate(int idLivre, String login) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `avis` WHERE `login`=? AND `idLivre`=?");
            ps.setString(1, login);
            ps.setInt(2, idLivre);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
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
    public int getLastIdAvis() {
        int lastID = 0;
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM `avis` ORDER BY id DESC LIMIT 1; ");
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                lastID = result.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return lastID;
    }

}

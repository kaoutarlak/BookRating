package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Double> moyenNoteByEvaluation(int idLivre) {
        Map<String, Double> resultMap = new HashMap<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT C.description, AVG(E.note) FROM `evaluation` AS E JOIN `avis` AS A ON E.idAvis=A.id " +
                    "JOIN `categorieEvaluation` AS C ON E.idCategorieEvaluation=C.id WHERE A.idLivre=? GROUP BY E.idCategorieEvaluation;");
            ps.setInt(1, idLivre);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String description = result.getString(1);
                Double moyenne = result.getDouble(2);
                resultMap.put(description, moyenne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return resultMap;
    }

    @Override
    public double moyenNoteByLivre(int idLivre) {
        double moyenne = 0;
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT AVG(E.note) FROM `evaluation` AS E JOIN `avis` AS A ON E.idAvis=A.id WHERE  A.idLivre=? ;");
            ps.setInt(1, idLivre);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                moyenne = result.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return moyenne;
    }

    @Override
    public int nbAvisByLivre(int idLivre) {
        int nombreAvis = 0;
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT count(*) FROM `avis` AS A  WHERE  A.idLivre=? ;");
            ps.setInt(1, idLivre);

            ResultSet result = ps.executeQuery();
            if (result.next()) {
                nombreAvis = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return nombreAvis;
    }

}

package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.AvisEvaluation;
import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;
import com.bookrating.Models.Entities.livre;

import java.sql.*;
import java.text.DecimalFormat;
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
    public List<evaluation> getAllEvaluationByAvis(int idAvis) {
        List<evaluation> evaluationList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `evaluations` WHERE idAvis=?; ");
            ps.setInt(1, idAvis);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                evaluation e = new evaluation();
                e.setId(rs.getInt("id"));
                e.setIdCategorieEvaluation(rs.getInt("idCategorieEvaluation"));
                e.setNote(rs.getInt("note"));
                e.setIdAvis(rs.getInt("idAvis"));

                evaluationList.add(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return evaluationList;
    }

    @Override
    public List<avis> getAllAvisByLivre(int idLivre) {
        List<avis> avisList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `avis` WHERE idLivre=?; ");
            ps.setInt(1, idLivre);
            ResultSet result1 = ps.executeQuery();
            while (result1.next()) {
                avis a = new avis();
                a.setId(result1.getInt("id"));
                a.setDatePost(result1.getDate("datePost").toLocalDate());
                a.setCommentaire(result1.getString("commentaire"));
                a.setNbLikes(result1.getInt("nbLikes"));
                a.setLogin(result1.getString("login"));
                a.setIdLivre(result1.getInt("idLivre"));

                avisList.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return avisList;

    }

    @Override
    public List<AvisEvaluation> getAllEvaluationByMember(String login) {
        List<AvisEvaluation> avisEvaluations = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `avis` WHERE login=? ORDER BY id;");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AvisEvaluation AE = new AvisEvaluation();
                AE.setId(rs.getInt("id"));
                AE.setDatePost(rs.getDate("datePost").toLocalDate());
                AE.setCommentaire(rs.getString("commentaire"));
                AE.setNbLikes(rs.getInt("nbLikes"));
                AE.setLogin(rs.getString("login"));
                AE.setIdLivre(rs.getInt("idLivre"));

                List<evaluation> evaluationList = new ArrayList<>();
                PreparedStatement p = null;
                try {
                    p = connection.prepareStatement("SELECT * FROM `evaluation` where idAvis=? ORDER BY idAvis;");
                    p.setInt(1, rs.getInt("id"));
                    ResultSet r = p.executeQuery();
                    while (r.next()) {
                        evaluation e = new evaluation();
                        e.setId(r.getInt("id"));
                        e.setIdCategorieEvaluation(r.getInt("idCategorieEvaluation"));
                        e.setNote(r.getInt("note"));
                        e.setIdAvis(r.getInt("idAvis"));
                        evaluationList.add(e);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                AE.setEvaluationList(evaluationList);
                avisEvaluations.add(AE);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return avisEvaluations;
    }

    @Override
    public List<livre> getAllLivreEvaluerByMember(String login) {
        List<livre> livreList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM `livre` AS L JOIN `avis` AS A ON L.id = A.idLivre WHERE A.login=? ORDER BY L.id; ");
            stm.setString(1, login);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                livre l = new livre();

                l.setId(result.getInt("id"));
                l.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setDateParution(LocalDate.parse(result.getString("dateParution")));
                l.setImage(result.getString("image"));
                l.setDescription(result.getString("description"));
                l.setAddBy(result.getString("addBy"));

                livreList.add(l);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return livreList;
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
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT C.description, AVG(E.note) FROM `evaluation` AS E JOIN `avis` AS A ON E.idAvis=A.id " +
                    "JOIN `categorieEvaluation` AS C ON E.idCategorieEvaluation=C.id WHERE A.idLivre=? GROUP BY E.idCategorieEvaluation;");
            ps.setInt(1, idLivre);

            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String description = result.getString(1);
                String moyenneString = df.format(result.getDouble(2));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
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

    @Override
    public void deleteAvis(int idAvis) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM `avis` WHERE  id=? ;");
            ps.setInt(1, idAvis);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterAvis(int idAvis, String commentaire) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = date.format(formatter);
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `avis` SET commentaire=? , `datePost`=? WHERE  id=? ;");
            ps.setString(1, commentaire);
            ps.setString(2, dateString);
            ps.setInt(3, idAvis);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void alterEvaluation(int idCat, int idAvis, int note) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `evaluation` SET `note`=? WHERE `idAvis`=? AND `idCategorieEvaluation`=? ;");
            ps.setInt(1, note);
            ps.setInt(2, idAvis);
            ps.setInt(3, idCat);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<avis> getAllAvis() {
        List<avis> avisList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM `avis` ");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                avis a = new avis();
                a.setId(result.getInt("id"));
                a.setDatePost(result.getDate("datePost").toLocalDate());
                a.setCommentaire(result.getString("commentaire"));
                a.setNbLikes(result.getInt("nbLikes"));
                a.setLogin(result.getString("login"));
                a.setIdLivre(result.getInt("idLivre"));

                avisList.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return avisList;
    }

    @Override
    public void likeCommentaire(int idAvis) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `avis` SET `nbLikes`=`nbLikes`+1 WHERE `id`=? ;");
            ps.setInt(1, idAvis);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

}

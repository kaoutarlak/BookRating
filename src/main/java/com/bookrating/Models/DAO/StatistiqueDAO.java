package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.LivreEvaluation;
import com.bookrating.Models.Entities.livre;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatistiqueDAO implements IStatistiqueDAO {

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

    /*-------------Statistique Membre -----------*/
    @Override
    public Map<String, Integer> statLivres() {
        Map<String, Integer> myMapResult = new HashMap<>();
        try {
            establichConnection();
            PreparedStatement ps1 = connection.prepareStatement("SELECT COUNT(*) AS nb_livre FROM livre; ");
            ResultSet result1 = ps1.executeQuery();
            if (result1.next()) {
                myMapResult.put("nb_livre", result1.getInt("nb_livre"));
            }
            PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(id) AS nb_livreLu FROM livreLu; ");
            ResultSet result2 = ps2.executeQuery();
            if (result2.next()) {
                myMapResult.put("nb_livreLu", result2.getInt("nb_livreLu"));
            }
            PreparedStatement ps3 = connection.prepareStatement("SELECT COUNT(id) AS nb_livreEvalue FROM avis; ");
            ResultSet result3 = ps3.executeQuery();
            if (result3.next()) {
                myMapResult.put("nb_livreEvalue", result3.getInt("nb_livreEvalue"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return myMapResult;
    }

    @Override
    public Map<String, Integer> statLivresParCategorie(int idCatLivre) {
        Map<String, Integer> myMapResult = new HashMap<>();
        try {
            establichConnection();
            PreparedStatement ps1 = connection.prepareStatement("SELECT COUNT(*) AS nb_livre FROM livre WHERE idCategorieLivre=?; ");
            ps1.setInt(1, idCatLivre);
            ResultSet result1 = ps1.executeQuery();
            if (result1.next()) {
                myMapResult.put("nb_livre", result1.getInt("nb_livre"));
            }
            PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(livreLu.id) AS nb_livreLu FROM livreLu " + "JOIN livre ON livreLu.idLivre = livre.id WHERE idCategorieLivre=?; ");
            ps2.setInt(1, idCatLivre);
            ResultSet result2 = ps2.executeQuery();
            if (result2.next()) {
                myMapResult.put("nb_livreLu", result2.getInt("nb_livreLu"));
            }
            PreparedStatement ps3 = connection.prepareStatement("SELECT COUNT(avis.id) AS nb_livreEvalue FROM avis " + "JOIN livre ON avis.idLivre = livre.id WHERE idCategorieLivre=?;");
            ps3.setInt(1, idCatLivre);
            ResultSet result3 = ps3.executeQuery();
            if (result3.next()) {
                myMapResult.put("nb_livreEvalue", result3.getInt("nb_livreEvalue"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return myMapResult;
    }

    @Override
    public List<LivreEvaluation> bestLivreNote() {
        List<LivreEvaluation> livreList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(avis.idLivre) AS nb_avis, AVG(avg_scores.note) AS moyenne " + "FROM livre JOIN avis ON livre.id = avis.idLivre JOIN ( SELECT idAvis, AVG(note) AS note " + "FROM evaluation GROUP BY idAvis ) AS avg_scores ON avg_scores.idAvis = avis.id GROUP BY livre.id ORDER BY nb_avis DESC LIMIT 3; ");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                LivreEvaluation l = new LivreEvaluation();
                l.setId(result.getInt("id"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setNombreEvaluation(result.getInt("nb_avis"));
                String moyenneString = df.format(result.getDouble("moyenne"));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
                l.setNoteMoyenne(moyenne);

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
    public List<LivreEvaluation> bestLivreNoteParCategorie(int idCatLivre) {
        List<LivreEvaluation> livreList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(avis.idLivre) AS nb_avis, AVG(avg_scores.note) AS moyenne " + "FROM livre JOIN avis ON livre.id = avis.idLivre JOIN ( SELECT idAvis, AVG(note) AS note " + "FROM evaluation GROUP BY idAvis ) AS avg_scores ON avg_scores.idAvis = avis.id WHERE livre.idCategorieLivre = ? " + "GROUP BY livre.id ORDER BY nb_avis DESC LIMIT 3; ");
            ps.setInt(1, idCatLivre);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                LivreEvaluation l = new LivreEvaluation();
                l.setId(result.getInt("id"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setNombreEvaluation(result.getInt("nb_avis"));
                String moyenneString = df.format(result.getDouble("moyenne"));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
                l.setNoteMoyenne(moyenne);

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
    public List<livre> livrePlusLu() {
        List<livre> livreList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(livreLu.idLivre) AS num_reads" + " FROM livre JOIN livreLu ON livre.id = livreLu.idLivre" + " GROUP BY livre.id ORDER BY num_reads DESC LIMIT 3; ");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                livre l = new livre();
                l.setId(result.getInt("id"));
                l.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setIdCategorieLivre(result.getInt("num_reads"));

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
    public List<livre> livrePlusLuParCategorie(int idCatLivre) {
        List<livre> livreList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(livreLu.idLivre) AS num_reads " + "FROM livre JOIN livreLu ON livre.id = livreLu.idLivre" + " WHERE livre.idCategorieLivre=? GROUP BY livre.id ORDER BY num_reads DESC LIMIT 3; ");
            ps.setInt(1, idCatLivre);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                livre l = new livre();
                l.setId(result.getInt("id"));
                l.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setIdCategorieLivre(result.getInt("num_reads"));

                livreList.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return livreList;
    }

    /*-------------Statistique Auteur -----------*/
    @Override
    public Map<String, Integer> statLivresAuteur(String auteur) {
        Map<String, Integer> myMapResult = new HashMap<>();
        try {
            establichConnection();
            PreparedStatement ps1 = connection.prepareStatement("SELECT COUNT(*) AS nb_livre FROM livre WHERE livre.addBy=?; ");
            ps1.setString(1, auteur);
            ResultSet result1 = ps1.executeQuery();
            if (result1.next()) {
                myMapResult.put("nb_livre", result1.getInt("nb_livre"));
            }
            PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(livreLu.id) AS nb_livreLu FROM livreLu "
                    + "JOIN livre ON livreLu.idLivre = livre.id WHERE livre.addBy=?; ");
            ps2.setString(1, auteur);
            ResultSet result2 = ps2.executeQuery();
            if (result2.next()) {
                myMapResult.put("nb_livreLu", result2.getInt("nb_livreLu"));
            }
            PreparedStatement ps3 = connection.prepareStatement("SELECT COUNT(avis.id) AS nb_livreEvalue FROM avis "
                    + "JOIN livre ON avis.idLivre = livre.id WHERE livre.addBy=?;");
            ps3.setString(1, auteur);
            ResultSet result3 = ps3.executeQuery();
            if (result3.next()) {
                myMapResult.put("nb_livreEvalue", result3.getInt("nb_livreEvalue"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return myMapResult;
    }

    @Override
    public Map<String, Integer> statLivresAuteurParCategorie(int idCatLivre, String auteur) {
        Map<String, Integer> myMapResult = new HashMap<>();
        try {
            establichConnection();
            PreparedStatement ps1 = connection.prepareStatement("SELECT COUNT(*) AS nb_livre FROM livre WHERE idCategorieLivre=? AND livre.addBy=?; ");
            ps1.setInt(1, idCatLivre);
            ps1.setString(2, auteur);
            ResultSet result1 = ps1.executeQuery();
            if (result1.next()) {
                myMapResult.put("nb_livre", result1.getInt("nb_livre"));
            }
            PreparedStatement ps2 = connection.prepareStatement("SELECT COUNT(livreLu.id) AS nb_livreLu FROM livreLu "
                    + "JOIN livre ON livreLu.idLivre = livre.id WHERE idCategorieLivre=? AND livre.addBy=?; ");
            ps2.setInt(1, idCatLivre);
            ps2.setString(2, auteur);
            ResultSet result2 = ps2.executeQuery();
            if (result2.next()) {
                myMapResult.put("nb_livreLu", result2.getInt("nb_livreLu"));
            }
            PreparedStatement ps3 = connection.prepareStatement("SELECT COUNT(avis.id) AS nb_livreEvalue FROM avis "
                    + "JOIN livre ON avis.idLivre = livre.id WHERE idCategorieLivre=? AND livre.addBy=?;");
            ps3.setInt(1, idCatLivre);
            ps3.setString(2, auteur);
            ResultSet result3 = ps3.executeQuery();
            if (result3.next()) {
                myMapResult.put("nb_livreEvalue", result3.getInt("nb_livreEvalue"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return myMapResult;
    }

    @Override
    public List<LivreEvaluation> bestLivreNoteAuteur(String auteur) {
        List<LivreEvaluation> livreList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(avis.idLivre) AS nb_avis, AVG(avg_scores.note) AS moyenne "
                    + "FROM livre JOIN avis ON livre.id = avis.idLivre JOIN ( SELECT idAvis, AVG(note) AS note "
                    + "FROM evaluation GROUP BY idAvis ) AS avg_scores ON avg_scores.idAvis = avis.id "
                    + "WHERE livre.addBy = ? GROUP BY livre.id ORDER BY nb_avis DESC LIMIT 3; ");
            ps.setString(1, auteur);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                LivreEvaluation l = new LivreEvaluation();
                l.setId(result.getInt("id"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setNombreEvaluation(result.getInt("nb_avis"));
                String moyenneString = df.format(result.getDouble("moyenne"));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
                l.setNoteMoyenne(moyenne);

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
    public List<LivreEvaluation> bestLivreAuteurNoteParCategorie(int idCatLivre, String auteur) {
        List<LivreEvaluation> livreList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(avis.idLivre) AS nb_avis, AVG(avg_scores.note) AS moyenne "
                    + "FROM livre JOIN avis ON livre.id = avis.idLivre JOIN ( SELECT idAvis, AVG(note) AS note "
                    + "FROM evaluation GROUP BY idAvis ) AS avg_scores ON avg_scores.idAvis = avis.id "
                    + "WHERE livre.idCategorieLivre = ? AND livre.addBy = ? "
                    + "GROUP BY livre.id ORDER BY nb_avis DESC LIMIT 3; ");
            ps.setInt(1, idCatLivre);
            ps.setString(2, auteur);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                LivreEvaluation l = new LivreEvaluation();
                l.setId(result.getInt("id"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setNombreEvaluation(result.getInt("nb_avis"));
                String moyenneString = df.format(result.getDouble("moyenne"));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
                l.setNoteMoyenne(moyenne);

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
    public List<livre> livrePlusLuAuteur(String auteur) {
        List<livre> livreList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(livreLu.idLivre) AS num_reads"
                    + " FROM livre JOIN livreLu ON livre.id = livreLu.idLivre WHERE livre.addBy=? "
                    + " GROUP BY livre.id ORDER BY num_reads DESC LIMIT 3; ");
            ps.setString(1, auteur);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                livre l = new livre();
                l.setId(result.getInt("id"));
                l.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setIdCategorieLivre(result.getInt("num_reads"));

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
    public List<livre> livrePlusLuAuteurParCategorie(int idCatLivre, String auteur) {
        List<livre> livreList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(livreLu.idLivre) AS num_reads "
                    + "FROM livre JOIN livreLu ON livre.id = livreLu.idLivre"
                    + " WHERE livre.idCategorieLivre=? AND livre.addBy=? "
                    + " GROUP BY livre.id ORDER BY num_reads DESC LIMIT 3; ");
            ps.setInt(1, idCatLivre);
            ps.setString(2, auteur);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                livre l = new livre();
                l.setId(result.getInt("id"));
                l.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setIdCategorieLivre(result.getInt("num_reads"));
                livreList.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return livreList;
    }

    /*-------------Statistique Admin -----------*/
    @Override
    public List<LivreEvaluation> statLivreAdmin() {
        List<LivreEvaluation> livreList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(avis.idLivre) AS nb_avis, AVG(avg_scores.note) AS moyenne "
                    + "FROM livre JOIN avis ON livre.id = avis.idLivre JOIN ( SELECT idAvis, AVG(note) AS note "
                    + "FROM evaluation GROUP BY idAvis ) AS avg_scores ON avg_scores.idAvis = avis.id "
                    + "GROUP BY livre.id ORDER BY nb_avis DESC ; ");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                LivreEvaluation l = new LivreEvaluation();
                l.setId(result.getInt("id"));
                l.setIdidCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setNombreEvaluation(result.getInt("nb_avis"));
                String moyenneString = df.format(result.getDouble("moyenne"));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
                l.setNoteMoyenne(moyenne);

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
    public List<LivreEvaluation> statLivreAdminParCategorie(int idCatLivre) {
        List<LivreEvaluation> livreList = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.#");
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT livre.*, COUNT(avis.idLivre) AS nb_avis, AVG(avg_scores.note) AS moyenne "
                    + "FROM livre JOIN avis ON livre.id = avis.idLivre JOIN ( SELECT idAvis, AVG(note) AS note "
                    + "FROM evaluation GROUP BY idAvis ) AS avg_scores ON avg_scores.idAvis = avis.id WHERE livre.idCategorieLivre = ? "
                    + "GROUP BY livre.id ORDER BY nb_avis DESC ; ");
            ps.setInt(1, idCatLivre);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                LivreEvaluation l = new LivreEvaluation();
                l.setId(result.getInt("id"));
                l.setIdidCategorieLivre(result.getInt("idCategorieLivre"));
                l.setTitre(result.getString("titre"));
                l.setNomAuteur(result.getString("nomAuteur"));
                l.setImage(result.getString("image"));
                l.setNombreEvaluation(result.getInt("nb_avis"));
                String moyenneString = df.format(result.getDouble("moyenne"));
                Double moyenne = Double.parseDouble(moyenneString.replace(",", "."));
                l.setNoteMoyenne(moyenne);

                livreList.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return livreList;
    }


}

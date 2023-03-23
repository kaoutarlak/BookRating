
package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.livre;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO implements ILivreDAO {
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
    public int nbPageLivre(String categorie) {
        int nbPageLivre = 0;
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ROUND(COUNT(*) / 10) FROM `livre` AS L JOIN `categorieLivre` AS C ON L.idCategorieLivre = C.id WHERE C.titre = '" + categorie + "'");

            if (rs.next()) {
                nbPageLivre = rs.getInt(1);
            }

            closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return nbPageLivre;
    }

    @Override
    public List<livre> getlistLivreByCat(String categorie) {
        try {
            establichConnection();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM `livre` AS L JOIN `categorieLivre` AS C ON L.idCategorieLivre = C.id WHERE C.titre = '" + categorie + "'");
            List<livre> livreList = new ArrayList<livre>();

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

            closeConnection();
            return livreList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public livre getLivre(int idLivre) {
        livre foundLivre = new livre();
        try {
            establichConnection();
            PreparedStatement ps= connection.prepareStatement("SELECT * FROM livre WHERE id=?");
            ps.setInt(1,idLivre);
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                foundLivre.setId(result.getInt("id"));
                foundLivre.setIdCategorieLivre(result.getInt("idCategorieLivre"));
                foundLivre.setTitre(result.getString("titre"));
                foundLivre.setNomAuteur(result.getString("nomAuteur"));
                foundLivre.setDateParution(LocalDate.parse(result.getString("dateParution")));
                foundLivre.setImage(result.getString("image"));
                foundLivre.setDescription(result.getString("description"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
        return foundLivre;
    }

    @Override
    public void addLivre(livre l) {
        LocalDate dateParution = l.getDateParution();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateParutionString = dateParution.format(formatter);
        try {
            establichConnection();
            PreparedStatement ps= connection.prepareStatement("INSERT INTO `livre`(`idCategorieLivre`, `titre`, `nomAuteur`, `dateParution`, `image`, `description`, `addBy`)" +
                    " VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1,l.getIdCategorieLivre());
            ps.setString(2,l.getTitre());
            ps.setString(3,l.getNomAuteur());
            ps.setString(4,dateParutionString);
            ps.setString(5,l.getImage());
            ps.setString(6,l.getDescription());
            ps.setString(7,l.getAddBy());
            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }finally {
            closeConnection();
        }
    }


}
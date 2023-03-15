
        package com.bookrating.Models.DAO;

        import com.bookrating.Models.Entities.categorieLivre;
        import com.bookrating.Models.Entities.livre;

        import java.sql.*;
        import java.time.LocalDate;
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
            ResultSet rs = stmt.executeQuery("SELECT ROUND(COUNT(*) / 8) FROM `livre` AS L JOIN `categorieLivre` AS C ON L.idCategorieLivre = C.id WHERE C.titre = '" + categorie + "'");

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

                livreList.add(l);
            }

            closeConnection();
            return livreList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
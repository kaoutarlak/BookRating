package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatLivresDAO implements ICatLivresDAO {
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
    public void addCategoryLivre(categorieLivre categorieLivre) {

    }

    @Override
    public List<categorieLivre> listCatLivres() {
        try {
            establichConnection();
            Statement st = connection.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM categorieLivre");
            List<categorieLivre> categorieLivres = new ArrayList<categorieLivre>();

            while (result.next()) {
                categorieLivre c = new categorieLivre();
                c.setId(result.getInt("id"));
                c.setTitre(result.getString("titre"));
                categorieLivres.add(c);
            }

            closeConnection();
            return categorieLivres;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

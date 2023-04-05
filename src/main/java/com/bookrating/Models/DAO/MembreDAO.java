package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.membre;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO implements IMembreDAO {

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
    public membre addMembre(membre membre) {
        String login = membre.getLogin();
        String password = membre.getPassword();
        String nom = membre.getNom();
        String prenom = membre.getPrenom();
        String telephone = membre.getTelephone();
        String adresse = membre.getAdresse();
        LocalDate dateNaissance = membre.getDateNaissance();
        LocalDate dateInscription = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateInscriptionString = dateInscription.format(formatter);
        String dateNaissanceString = (dateNaissance != null) ? dateNaissance.format(formatter) : "";
        int active = 1;
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO `membre`(`login`, `password`, `nom`, `prenom`, `telephone`, `adresse`, `dateNaissance`, `dateInsscription`, `active`) " +
                    " VALUES ('" + login + "','" + password + "','" + nom + "','" + prenom + "','" + telephone + "','" + adresse + "','" + dateNaissanceString + "','" + dateInscriptionString + "'," + active + ")");
            closeConnection();
            return membre;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExistLogin(String login) {
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT login FROM `membre` WHERE login = '" + login + "'");

            if (rs.next()) {
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
    public membre login(String login, String password) {
        membre membreConnect = new membre();
        try {
            establichConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `membre` WHERE login = '" + login + "' AND password= '" + password + "' AND active=1");

            while (rs.next()) {
                membreConnect.setLogin(rs.getString("login"));
                membreConnect.setPassword(rs.getString("password"));
                membreConnect.setNom(rs.getString("nom"));
                membreConnect.setPrenom(rs.getString("prenom"));
                membreConnect.setTelephone(rs.getString("telephone"));
                membreConnect.setAdresse(rs.getString("adresse"));
                membreConnect.setPhoto(rs.getString("photo"));
                membreConnect.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                membreConnect.setDateInsscription(rs.getDate("dateInsscription").toLocalDate());
                membreConnect.setActive(rs.getByte("active"));
            }
            closeConnection();
            return membreConnect;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String membreRole(String login) {
        String role;
        try {
            establichConnection();
            PreparedStatement stmtAdmin = connection.prepareStatement("SELECT * FROM administrateur WHERE login = ?");
            stmtAdmin.setString(1, login);
            ResultSet resultAdmin = stmtAdmin.executeQuery();

            PreparedStatement stmtAuteur = connection.prepareStatement("SELECT * FROM auteur WHERE login = ?");
            stmtAuteur.setString(1, login);
            ResultSet resultAuteur = stmtAuteur.executeQuery();

            if (resultAdmin.next()) {
                role = "admin";
            } else if (resultAuteur.next()) {
                role = "auteur";
            } else {
                role = "membre";
            }
            closeConnection();
            return role;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<membre> allUtilisateur() {
        List<membre> membres = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM membre;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                membre m = new membre();
                m.setLogin(rs.getString("login"));
                m.setPassword(rs.getString("password"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setTelephone(rs.getString("telephone"));
                m.setAdresse(rs.getString("adresse"));
                m.setPhoto(rs.getString("photo"));
                m.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                m.setDateInsscription(rs.getDate("dateInsscription").toLocalDate());
                m.setActive(rs.getByte("active"));
                membres.add(m);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return membres;
    }

    @Override
    public List<String> getAllMembreMailActive() {
        List<String> adresseList = new ArrayList<>();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT(adresse) FROM membre WHERE active=1 and login NOT IN (select login from administrateur); ");
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                String adresse = result.getString(1);
                adresseList.add(adresse);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return adresseList;
    }

    @Override
    public membre getMembre(String login) {
        membre m = new membre();
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM membre WHERE login=?;");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                m.setLogin(rs.getString("login"));
                m.setPassword(rs.getString("password"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setTelephone(rs.getString("telephone"));
                m.setAdresse(rs.getString("adresse"));
                m.setPhoto(rs.getString("photo"));
                m.setDateNaissance(rs.getDate("dateNaissance").toLocalDate());
                m.setDateInsscription(rs.getDate("dateInsscription").toLocalDate());
                m.setActive(rs.getByte("active"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
        return m;
    }

    @Override
    public void alterMember(membre m) {
        LocalDate dateNaissance = m.getDateNaissance();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateNaissString = dateNaissance.format(formatter);
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `membre` SET `password`=?," +
                    "`nom`=?,`prenom`=?,`telephone`=?,`dateNaissance`=?,`photo`=? WHERE `login`=?;");
            ps.setString(1, m.getPassword());
            ps.setString(2, m.getNom());
            ps.setString(3, m.getPrenom());
            ps.setString(4, m.getTelephone());
            ps.setString(5, dateNaissString);
            ps.setString(6, m.getPhoto());
            ps.setString(7, m.getLogin());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void desactiverCompte(String login) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `membre` SET `active`=0 WHERE `login`=?;");
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void activerCompte(String login) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE `membre` SET `active`=1 WHERE `login`=?;");
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteCompte(String login) {
        try {
            establichConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE  FROM `membre` WHERE `login`=?;");
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }


}

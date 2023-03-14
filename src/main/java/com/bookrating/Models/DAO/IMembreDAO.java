package com.bookrating.Models.DAO;


import com.bookrating.Models.Entities.membre;

import java.util.List;

public interface IMembreDAO {

    void establichConnection();

    void closeConnection();

    membre addMembre(membre membre);

    boolean isExistLogin(String login);

    membre login(String login, String password);

    String membreRole(String login);
    List<membre> allUtilisateur();

}

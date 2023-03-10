package com.bookrating.Models.DAO;


import com.bookrating.Models.Entities.membre;

import java.util.List;

public interface IMembreDAO {

    void establichConnection();

    void closeConnection();

    membre addMembre(membre membre);

    membre login(String login, String password);

    List<membre> allUtilisateur();

}

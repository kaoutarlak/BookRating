package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.auteur;
import com.bookrating.Models.Entities.livre;

import java.util.List;

public interface IAuteurDAO {
    void establichConnection();

    void closeConnection();

    void addAuteur(auteur newAuteur);

    List<livre> getlivreAddByAuteur(String auteur);

    String getNomCompletAuteur(String login);
}
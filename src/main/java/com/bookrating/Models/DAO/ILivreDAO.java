package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.livre;

import java.util.List;

public interface ILivreDAO {
    void establichConnection();

    void closeConnection();

    int nbPageLivre(String categorie);

    List<livre> getlistLivreByCat(String categorie);
}

package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.livre;

import java.util.List;

public interface ILivreDAO {
    void establichConnection();

    void closeConnection();

    int nbPageLivre(String categorie);

    List<livre> getlistLivreByCat(String categorie);

    livre getLivre(int idLivre);

    void addLivre(livre l);

    List<livre> livreByCatFiltreTitreASC(String categorie);

    List<livre> livreByCatFiltreTitreDESC(String categorie);

    List<livre> livreByCatFiltreAuteurASC(String categorie);

    List<livre> livreByCatFiltreAuteurDESC(String categorie);

    List<livre> getAllLivres();

    void alterLivre(livre l);

    List<livre> getSearchLivre(String motChercher);
}
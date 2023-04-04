package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.AvisEvaluation;
import com.bookrating.Models.Entities.auteur;
import com.bookrating.Models.Entities.livre;

import java.util.List;

public interface IAuteurDAO {
    void establichConnection();

    void closeConnection();

    void addAuteur(auteur newAuteur);

    List<livre> getlivreAddByAuteur(String auteur);

    String getNomCompletAuteur(String login);
    List<AvisEvaluation> avisLivreAuteur(int idLivre);
    List<livre> getlivreGererByAuteur(String auteur);
}
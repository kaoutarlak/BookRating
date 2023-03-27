package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.LivreEvaluation;
import com.bookrating.Models.Entities.livre;

import java.util.*;

public interface IStatistiqueDAO {

    void establichConnection();
    void closeConnection();

    Map<String,Integer> statLivres();
    Map<String,Integer> statLivresParCategorie(int idCatLivre);
    List<LivreEvaluation> bestLivreNote();
    List<LivreEvaluation> bestLivreNoteParCategorie(int idCatLivre);
    List<livre> livrePlusLu();
    List<livre> livrePlusLuParCategorie(int idCatLivre);

}

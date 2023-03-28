package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.LivreEvaluation;
import com.bookrating.Models.Entities.livre;

import java.util.*;

public interface IStatistiqueDAO {

    void establichConnection();
    void closeConnection();

    /*-------------Statistique Membre -----------*/
    Map<String,Integer> statLivres();
    Map<String,Integer> statLivresParCategorie(int idCatLivre);
    List<LivreEvaluation> bestLivreNote();
    List<LivreEvaluation> bestLivreNoteParCategorie(int idCatLivre);
    List<livre> livrePlusLu();
    List<livre> livrePlusLuParCategorie(int idCatLivre);

    /*-------------Statistique Auteur -----------*/
    Map<String,Integer> statLivresAuteur(String auteur);
    Map<String,Integer> statLivresAuteurParCategorie(int idCatLivre,String auteur);
    List<LivreEvaluation> bestLivreNoteAuteur(String auteur);
    List<LivreEvaluation> bestLivreAuteurNoteParCategorie(int idCatLivre,String auteur);
    List<livre> livrePlusLuAuteur(String auteur);
    List<livre> livrePlusLuAuteurParCategorie(int idCatLivre,String auteur);

    /*-------------Statistique Admin -----------*/
    List<LivreEvaluation> statLivreAdmin();
    List<LivreEvaluation> statLivreAdminParCategorie(int idCatLivre);
}

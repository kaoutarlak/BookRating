package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.AvisEvaluation;
import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;
import com.bookrating.Models.Entities.livre;

import java.util.List;
import java.util.Map;

public interface IEvaluationDAO {

    void establichConnection();

    void closeConnection();

    void addAvis(avis newAvis);

    void addEvaluationByAvis(evaluation newEvaluation);

    List<evaluation> getAllEvaluationByAvis(int idAvis);

    List<avis> getAllAvisByLivre(int idLivre);

    List<AvisEvaluation> getAllEvaluationByMember(String login);

    List<livre> getAllLivreEvaluerByMember(String login);

    boolean areadyEvaluate(int idLivre, String login);

    int getLastIdAvis();

    Map<String, Double> moyenNoteByEvaluation(int idLivre);

    double moyenNoteByLivre(int idLivre);

    int nbAvisByLivre(int idLivre);

    void deleteAvis(int idAvis);

    void alterAvis(int idAvis, String commentaire);

    void alterEvaluation(int idCat, int idAvis, int note);

}
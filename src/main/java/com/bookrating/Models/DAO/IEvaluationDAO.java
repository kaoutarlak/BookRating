package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;

import java.util.List;
import java.util.Map;

public interface IEvaluationDAO {

    void establichConnection();

    void closeConnection();

    void addAvis(avis newAvis);

    void addEvaluationByAvis(evaluation newEvaluation);

    List<evaluation> getAllEvaluationByMember(String login);

    List<evaluation> getAllEvaluationByLivre(int idLivre);

    List<evaluation> getAllEvaluation();

    boolean areadyEvaluate(int idLivre, String login);

    int getLastIdAvis();

    Map<String, Double> moyenNoteByEvaluation(int idLivre);

    double moyenNoteByLivre(int idLivre);

    int nbAvisByLivre(int idLivre);

}
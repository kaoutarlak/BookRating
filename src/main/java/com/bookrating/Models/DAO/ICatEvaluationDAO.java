package com.bookrating.Models.DAO;


import com.bookrating.Models.Entities.categorieEvaluation;
import com.bookrating.Models.Entities.categorieLivre;

import java.util.List;

public interface ICatEvaluationDAO {

    void establichConnection();

    void closeConnection();

    void addCategorieEvaluation(categorieEvaluation categorieEvaluation );

    void alterCategorieEvaluation(categorieEvaluation categorieEvaluation );

    List<categorieEvaluation> getAllCategoriesEvaluation();


}

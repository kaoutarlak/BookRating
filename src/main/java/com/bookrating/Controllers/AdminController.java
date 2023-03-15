package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.categorieEvaluation;
import com.bookrating.Models.Entities.categorieLivre;
import com.bookrating.Models.Entities.livre;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/Admin")
public class AdminController {

    @RequestMapping(value = "/Evaluation/Categorie", method = RequestMethod.GET)
    public ModelAndView listLivre(HttpServletRequest request) {

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }

        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        List<categorieEvaluation> categorieEvaluationList =catEvaluationDAO.getAllCategoriesEvaluation();

        ModelAndView CategorieEvaluationJSP = new ModelAndView("Admin/CategorieEvaluation");
        CategorieEvaluationJSP.addObject("login", login);
        CategorieEvaluationJSP.addObject("categorieEvaluationList", categorieEvaluationList);

        return CategorieEvaluationJSP;
    }

    @RequestMapping(value = "/Evaluation/Categorie/Add", method = RequestMethod.POST)
    public ModelAndView addCategorieEvaluation(categorieEvaluation newCategorieEvaluation) {


        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        catEvaluationDAO.addCategorieEvaluation(newCategorieEvaluation);

        return new ModelAndView("redirect:/Admin/Evaluation/Categorie"); // rediriger vers la page list categorie
    }

    @RequestMapping(value = "/Evaluation/Categorie/Alter", method = RequestMethod.POST)
    public ModelAndView alterCategorieEvaluation(categorieEvaluation newCategorieEvaluation) {


        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        catEvaluationDAO.alterCategorieEvaluation(newCategorieEvaluation);

        return new ModelAndView("redirect:/Admin/Evaluation/Categorie"); // rediriger vers la page list categorie
    }

}
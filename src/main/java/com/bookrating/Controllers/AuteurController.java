package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.categorieEvaluation;
import com.bookrating.Models.Entities.categorieLivre;
import com.bookrating.Models.Entities.livre;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/Auteur")
public class AuteurController {

    @RequestMapping(value = "/MesLivres", method = RequestMethod.GET)
    public ModelAndView listLivreAuteur(HttpServletRequest request) {

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        ModelAndView viewMesLivres = new ModelAndView("Auteur/MesLivres");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        IAuteurDAO auteurDAO = new AuteurDAO();
        List<livre> livres = auteurDAO.getlivreAddByAuteur(login);

        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("livres", livres);

        return viewMesLivres;
    }

    @RequestMapping(value = "/livre/Add", method = RequestMethod.POST)
    public ModelAndView addLivre(livre newLivre) {


//        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
//        catEvaluationDAO.addCategorieEvaluation(newCategorieEvaluation);
//
        return new ModelAndView("redirect:/Admin/Evaluation/Categorie"); // rediriger vers la page list categorie
    }



}
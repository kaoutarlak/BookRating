package com.bookrating.Controllers;

import com.bookrating.Models.DAO.CatLivresDAO;
import com.bookrating.Models.DAO.ICatLivresDAO;
import com.bookrating.Models.DAO.IStatistiqueDAO;
import com.bookrating.Models.DAO.StatistiqueDAO;
import com.bookrating.Models.Entities.LivreEvaluation;
import com.bookrating.Models.Entities.categorieLivre;
import com.bookrating.Models.Entities.livre;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Statistique")
public class StatistiqueController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView signaleCommentaire(HttpServletRequest request) {

        ModelAndView statView = new ModelAndView("Membre/Statistiques");

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        IStatistiqueDAO statistiqueDAO = new StatistiqueDAO();
        Map<String, Integer> statMap = statistiqueDAO.statLivres();

        List<LivreEvaluation> livrePlusNote = statistiqueDAO.bestLivreNote();
        List<livre> livrePlusLu = statistiqueDAO.livrePlusLu();

        Boolean effaceFiltre = false;

        statView.addObject("catLivreList", catLivreList);
        statView.addObject("login", login);
        statView.addObject("statMap", statMap);
        statView.addObject("livrePlusNote", livrePlusNote);
        statView.addObject("livrePlusLu", livrePlusLu);
        statView.addObject("effaceFiltre", effaceFiltre);

        return statView;
    }

    @RequestMapping(value = "/Categorie", method = RequestMethod.POST)
    public ModelAndView signaleCommentaire(HttpServletRequest request, @RequestParam("categorie") int categorie) {

        ModelAndView statView = new ModelAndView("Membre/Statistiques");

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        IStatistiqueDAO statistiqueDAO = new StatistiqueDAO();
        Map<String, Integer> statMap = statistiqueDAO.statLivresParCategorie(categorie);

        List<LivreEvaluation> livrePlusNote = statistiqueDAO.bestLivreNoteParCategorie(categorie);
        List<livre> livrePlusLu = statistiqueDAO.livrePlusLuParCategorie(categorie);

        Boolean effaceFiltre = true;

        statView.addObject("catLivreList", catLivreList);
        statView.addObject("login", login);
        statView.addObject("statMap", statMap);
        statView.addObject("livrePlusNote", livrePlusNote);
        statView.addObject("livrePlusLu", livrePlusLu);
        statView.addObject("effaceFiltre", effaceFiltre);

        return statView;
    }

}
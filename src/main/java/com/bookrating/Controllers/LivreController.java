package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
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
@RequestMapping(value = "/Livres")
public class LivreController {

    @RequestMapping(value = "/Liste/{categorie}/{pagination}", method = RequestMethod.GET)
    public ModelAndView listLivre(@PathVariable String categorie, @PathVariable int pagination, HttpServletRequest request) {

        String login = "";
        String role = "";
        IMembreDAO membreDAO = new MembreDAO();

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
                role = membreDAO.membreRole(login);
            }
        }

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        ILivreDAO livreDAO = new LivreDAO();
        int nbPageLivre = livreDAO.nbPageLivre(categorie);
        List<livre> livres = livreDAO.getlistLivreByCat(categorie);

        int idLivreBegin = (pagination - 1) * 10;
        int idLivreEnd = idLivreBegin + 9;


        ModelAndView listeLivresModel = new ModelAndView("listeLivres");
        listeLivresModel.addObject("categorie", categorie);
        listeLivresModel.addObject("catLivreList", catLivreList);
        listeLivresModel.addObject("login", login);
        listeLivresModel.addObject("role", role);
        listeLivresModel.addObject("nbPageLivre", nbPageLivre);
        listeLivresModel.addObject("livres", livres);
        listeLivresModel.addObject("idLivreBegin", idLivreBegin);
        listeLivresModel.addObject("idLivreEnd", idLivreEnd);
        listeLivresModel.addObject("currentPage", pagination);
        return listeLivresModel;
    }

    @RequestMapping(value = "/AddLivreLu/{login}/{idLivre}", method = RequestMethod.GET)
    public void addLivreLu(@PathVariable String login, @PathVariable int idLivre) {

        ILivreLuDAO livreLuDAO = new LivreLuDAO();
        livreLuDAO.addLivreLu(login, idLivre);

    }

    @RequestMapping(value = "/LivresLus/{pagination}", method = RequestMethod.GET)
    public ModelAndView listLivreLu(@PathVariable int pagination, HttpServletRequest request) {

        String login = "";
        String role = "";
        IMembreDAO membreDAO = new MembreDAO();

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
                role = membreDAO.membreRole(login);
            }
        }

        int idLivreBegin = (pagination - 1) * 10;
        int idLivreEnd = idLivreBegin + 9;

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        ILivreLuDAO livreDAO = new LivreLuDAO();
        int nbPageLivre = livreDAO.nbPageLivreLu(login);
        List<livre> livres = livreDAO.getAllLivreLu(login);

        ModelAndView listeLivresLusModel = new ModelAndView("listeLivresLus");
        listeLivresLusModel.addObject("catLivreList", catLivreList);
        listeLivresLusModel.addObject("login", login);
        listeLivresLusModel.addObject("role", role);
        listeLivresLusModel.addObject("nbPageLivre", nbPageLivre);
        listeLivresLusModel.addObject("livres", livres);
        listeLivresLusModel.addObject("idLivreBegin", idLivreBegin);
        listeLivresLusModel.addObject("idLivreEnd", idLivreEnd);
        listeLivresLusModel.addObject("currentPage", pagination);
        return listeLivresLusModel;
    }
}
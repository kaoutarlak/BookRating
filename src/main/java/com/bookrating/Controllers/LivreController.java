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

    @RequestMapping(value = "/Liste/{categorie}", method = RequestMethod.GET)
    public ModelAndView connexion(@PathVariable String categorie, HttpServletRequest request) {

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

        ModelAndView listeLivresModel = new ModelAndView("listeLivres");
        listeLivresModel.addObject("categorie", categorie);
        listeLivresModel.addObject("catLivreList", catLivreList);
        listeLivresModel.addObject("login", login);
        listeLivresModel.addObject("role", role);
        listeLivresModel.addObject("nbPageLivre", nbPageLivre);
        listeLivresModel.addObject("livres", livres);
        return listeLivresModel;
    }
}
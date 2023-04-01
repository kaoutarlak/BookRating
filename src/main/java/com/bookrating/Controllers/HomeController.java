package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.auteur;
import com.bookrating.Models.Entities.categorieLivre;
import com.bookrating.Models.Entities.membre;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {
//    @Autowired
//    private EmailService sendMail ;

    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public ModelAndView home(HttpServletRequest request) {

        String login = "";
        String role = "";
        IMembreDAO membreDAO = new MembreDAO();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("login")) {
                    login = c.getValue();
                    role = membreDAO.membreRole(login);
                }
            }
        }

        ModelAndView model = new ModelAndView("Home");
        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();
        membre membre = membreDAO.getMembre(login);
        model.addObject("membre", membre);
        model.addObject("catLivreList", catLivreList);
        model.addObject("login", login);
        model.addObject("role", role);
        return model;
    }

    @RequestMapping(value = "/Connexion", method = RequestMethod.GET)
    public ModelAndView connexion() {

        ModelAndView connexionModel = new ModelAndView("Connexion");
        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        connexionModel.addObject("catLivreList", catLivreList);
        return connexionModel;
    }

    @RequestMapping(value = "/Connexion", method = RequestMethod.POST)
    public ModelAndView connexion(membre membre, HttpServletResponse response) {

        IMembreDAO membreDAO = new MembreDAO();
        membre membreLogin = membreDAO.login(membre.getLogin(), membre.getPassword());
        String role = membreDAO.membreRole(membreLogin.getLogin());

        if (membreLogin.getLogin() != null) {
            Cookie cookie = new Cookie("login", membre.getLogin());
            cookie.setPath("/");
            response.addCookie(cookie);

            switch (role) {
                case "admin":
                    return new ModelAndView("redirect:/Home"); // rediriger vers la page Home Membre
                case "auteur":
                    return new ModelAndView("redirect:/Auteur/MesLivres"); // rediriger vers la page Home Auteur
                case "membre":
                    return new ModelAndView("redirect:/Home"); // rediriger vers la page Home Membre
                default:
                    ModelAndView connexionModel = new ModelAndView("Connexion");
                    connexionModel.addObject("error", "Login ou mot de passe est incorrect !!");
                    return connexionModel;
            }
        } else {
            membre m = membreDAO.getMembre(membre.getLogin());
            String message = "Login ou mot de passe est incorrect !!";
            if (m.getActive() == 0 && m.getAdresse() != null) {
                message = "Ce compte est désactivé pour le moment !";
            }
            ModelAndView connexionModel = new ModelAndView("Connexion");
            connexionModel.addObject("error", message);
            return connexionModel;
        }
    }

    @RequestMapping(value = "/Inscription", method = RequestMethod.GET)
    public ModelAndView inscription() {

        ModelAndView inscriptionModel = new ModelAndView("Inscription");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        inscriptionModel.addObject("catLivreList", catLivreList);

        return inscriptionModel;

    }

    @RequestMapping(value = "/InscriptionType", method = RequestMethod.POST)
    public ModelAndView inscriptionType(@RequestParam("role") String role) {

        ModelAndView inscriptionModel = new ModelAndView();

        if (role.equals("auteur")) {
            inscriptionModel.setViewName("Auteur/Inscription");
        } else {
            inscriptionModel.setViewName("Inscription");
        }

        return inscriptionModel;

    }

    @RequestMapping(value = "/Inscription", method = RequestMethod.POST)
    public ModelAndView inscription(membre newMembre) {


        IMembreDAO membreDAO = new MembreDAO();
        Boolean existLogin = membreDAO.isExistLogin(newMembre.getLogin());
        if (!existLogin) {
            membreDAO.addMembre(newMembre);
            ModelAndView connexionModel = new ModelAndView("Connexion");
            connexionModel.addObject("newMembre", newMembre);
            return connexionModel;
        } else {
            ModelAndView inscriptionModel = new ModelAndView("Inscription");
            inscriptionModel.addObject("message", "Login existe déjà !!! veuillez choisir un autre.");
            inscriptionModel.addObject("infoSaisi", newMembre);
            return inscriptionModel;
        }

    }

    @RequestMapping(value = "/InscriptionAuteur", method = RequestMethod.POST)
    public ModelAndView inscriptionAuteut(membre newMembre, @RequestParam("code") String code) {
        IMembreDAO membreDAO = new MembreDAO();
        IAuteurDAO auteurDAO = new AuteurDAO();
        Boolean existLogin = membreDAO.isExistLogin(newMembre.getLogin());
        if (!existLogin) {
            membreDAO.addMembre(newMembre);
            auteur newAuteur = new auteur();
            newAuteur.setCode(code);
            newAuteur.setLogin(newMembre.getLogin());
            auteurDAO.addAuteur(newAuteur);
            ModelAndView connexionModel = new ModelAndView("Connexion");
            connexionModel.addObject("newMembre", newMembre);
            return connexionModel;
        } else {
            ModelAndView inscriptionModel = new ModelAndView("Auteur/Inscription");
            inscriptionModel.addObject("message", "Login existe déjà !!! veuillez choisir un autre.");
            inscriptionModel.addObject("infoSaisi", newMembre);
            inscriptionModel.addObject("code", code);
            return inscriptionModel;
        }
    }

    @RequestMapping(value = "/Confirmation", method = RequestMethod.GET)
    public ModelAndView confirmation() {

        ModelAndView confirmationModel = new ModelAndView("Confirmation");

        return confirmationModel;
    }

    @RequestMapping(value = "/Deconnexion", method = RequestMethod.GET)
    public ModelAndView deconnexion(HttpServletResponse response, HttpServletRequest request) {

        Cookie cookieToDelete = new Cookie("login", null);
        cookieToDelete.setMaxAge(0);
        cookieToDelete.setPath("/");
        response.addCookie(cookieToDelete);

        return new ModelAndView("redirect:/Home"); // rediriger vers la page Home
    }

    @RequestMapping(value = "/Profil", method = RequestMethod.GET)
    public ModelAndView profil(HttpServletRequest request) {

        String login = "";
        String role = "";
        IMembreDAO membreDAO = new MembreDAO();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("login")) {
                    login = c.getValue();
                    role = membreDAO.membreRole(login);
                }
            }
        }

        ModelAndView model = new ModelAndView("Profil");
        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();
        membre membre = membreDAO.getMembre(login);
        model.addObject("membre", membre);
        model.addObject("catLivreList", catLivreList);
        model.addObject("login", login);
        model.addObject("role", role);
        return model;
    }

    @RequestMapping(value = "/ModifierProfil", method = RequestMethod.POST)
    public ModelAndView modifierProfil(membre membre) {

        IMembreDAO membreDAO = new MembreDAO();
        membreDAO.alterMember(membre);
        return new ModelAndView("redirect:/Profil");

    }

    @RequestMapping(value = "/DesactiveCompte/{login}", method = RequestMethod.GET)
    public ModelAndView desactiveCompte(@PathVariable String login) {

        IMembreDAO membreDAO = new MembreDAO();
        membreDAO.desactiverCompte(login);
        return new ModelAndView("redirect:/Connexion");

    }

}

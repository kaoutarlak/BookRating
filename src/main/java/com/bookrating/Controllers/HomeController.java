package com.bookrating.Controllers;

import com.bookrating.Models.DAO.IMembreDAO;
import com.bookrating.Models.DAO.MembreDAO;
import com.bookrating.Models.Entities.membre;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
                role = membreDAO.membreRole(login);
            }
        }

        ModelAndView model = new ModelAndView("Home");
        model.addObject("login", login);
        model.addObject("role", role);
        return model;
    }

    @RequestMapping(value = "/Connexion", method = RequestMethod.GET)
    public ModelAndView connexion() {

        ModelAndView connexionModel = new ModelAndView("Connexion");

        return connexionModel;
    }

    @RequestMapping(value = "/Connexion", method = RequestMethod.POST)
    public ModelAndView connexion(membre membre, HttpServletResponse response) {


        IMembreDAO membreDAO = new MembreDAO();
        membre membreLogin = membreDAO.login(membre.getLogin(), membre.getPassword());
        if (membreLogin.getLogin() != null) {
            Cookie cookie = new Cookie("login", membre.getLogin());
            cookie.setPath("/");
            response.addCookie(cookie);

            return new ModelAndView("redirect:/Home"); // rediriger vers la page Home

        } else {
            ModelAndView connexionModel = new ModelAndView("Connexion");
            connexionModel.addObject("error", "Login ou mot de passe est incorrect !!");
            return connexionModel;
        }
    }

    @RequestMapping(value = "/Inscription", method = RequestMethod.GET)
    public ModelAndView inscription() {

        ModelAndView inscriptionModel = new ModelAndView("Inscription");
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
            inscriptionModel.addObject("message", "login existe déjà !!! veuillez choisir un autre.");
            return inscriptionModel;
        }

        //sendMail.sendMailConfirmation(newMembre.getAdresse(),newMembre.getLogin());


    }

    @RequestMapping(value = "/Confirmation", method = RequestMethod.GET)
    public ModelAndView confirmation() {

        ModelAndView confirmationModel = new ModelAndView("Confirmation");

        return confirmationModel;
    }

    @RequestMapping(value = "/Deconnexion", method = RequestMethod.GET)
    public ModelAndView deconnexion(HttpServletResponse response,HttpServletRequest request) {

//        Cookie[] cookies = request.getCookies();
//        for (Cookie c : cookies) {
//            if (c.getName().equals("login")) {
//                c.setValue("");
//                c.setMaxAge(0);
//                c.setVersion(2);
//                response.addCookie(c);
//            }
//        }

        Cookie cookieToDelete = new Cookie("login", null);
        cookieToDelete.setMaxAge(0);
        cookieToDelete.setPath("/");
        response.addCookie(cookieToDelete);

        ModelAndView connexionModel = new ModelAndView("Connexion");

        return connexionModel;
    }

}

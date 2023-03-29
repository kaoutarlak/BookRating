package com.bookrating.Controllers;

import com.bookrating.EmailService;
import com.bookrating.Models.DAO.CatEvaluationDAO;
import com.bookrating.Models.DAO.ICatEvaluationDAO;
import com.bookrating.Models.DAO.IMembreDAO;
import com.bookrating.Models.DAO.MembreDAO;
import com.bookrating.Models.Entities.Mail;
import com.bookrating.Models.Entities.categorieEvaluation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/Admin")
public class AdminController {

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
    public ModelAndView sendEmail(HttpServletRequest request) {
        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        IMembreDAO membreDAO = new MembreDAO();
        List<String> membreMail = membreDAO.getAllMembreMailActive();
        ModelAndView mailView = new ModelAndView("Admin/SendMail");
        mailView.addObject("login", login);
        mailView.addObject("membreMail", membreMail);
        return mailView;
    }

    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public ModelAndView sendEmail(HttpServletRequest request, Mail mail) {
        String login = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        List<String> adresseList = new ArrayList<>();
        IMembreDAO membreDAO = new MembreDAO();
        if (mail.getMembre().equals("All")) {
            adresseList = membreDAO.getAllMembreMailActive();
        }else{
            adresseList.add(mail.getMembre());
        }
        try {
            emailService.sendEmail(adresseList, mail.getSujet(), mail.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        ModelAndView confirmMailView = new ModelAndView("Admin/Confirmation");
        return confirmMailView;
    }

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
        List<categorieEvaluation> categorieEvaluationList = catEvaluationDAO.getAllCategoriesEvaluation();

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
package com.bookrating.Controllers;

import com.bookrating.EmailService;
import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        membre membre = membreDAO.getMembre(login);

        mailView.addObject("membre", membre);
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
        } else {
            adresseList.add(mail.getMembre());
        }
        try {
            emailService.sendEmail(adresseList, mail.getSujet(), mail.getMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return new ModelAndView("redirect:/Admin/Confirmation");
    }

    @RequestMapping(value = "/Confirmation", method = RequestMethod.GET)
    public ModelAndView confirmation(HttpServletRequest request) {
        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        ModelAndView confirmView = new ModelAndView("Admin/Confirmation");
        confirmView.addObject("membre", membre);
        confirmView.addObject("login", login);
        return confirmView;
    }

    @RequestMapping(value = "/Evaluation/Categorie", method = RequestMethod.GET)
    public ModelAndView listEvaluationCategorie(HttpServletRequest request) {

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }

        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        List<categorieEvaluation> categorieEvaluationList = catEvaluationDAO.getAllCategoriesEvaluation();
        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        ModelAndView CategorieEvaluationJSP = new ModelAndView("Admin/CategorieEvaluation");
        CategorieEvaluationJSP.addObject("login", login);
        CategorieEvaluationJSP.addObject("categorieEvaluationList", categorieEvaluationList);
        CategorieEvaluationJSP.addObject("membre", membre);
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

    @RequestMapping(value = "/ListeLivres", method = RequestMethod.GET)
    public ModelAndView ListeLivres(HttpServletRequest request) {

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        ModelAndView viewMesLivres = new ModelAndView("Admin/ListeLivres");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        ILivreDAO livreDAO = new LivreDAO();
        List<livre> livres = livreDAO.getAllLivres();

        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        viewMesLivres.addObject("membre", membre);
        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("livres", livres);

        return viewMesLivres;
    }

    @RequestMapping(value = "/ListeLivres/Categorie", method = RequestMethod.POST)
    public ModelAndView ListeLivresByCategorie(HttpServletRequest request, @RequestParam("categorie") String categorie) {

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        ModelAndView viewMesLivres = new ModelAndView("Admin/ListeLivres");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        ILivreDAO livreDAO = new LivreDAO();
        List<livre> livres = livreDAO.getlistLivreByCat(categorie);

        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        viewMesLivres.addObject("membre", membre);
        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("livres", livres);
        viewMesLivres.addObject("effaceFiltre", 1);

        return viewMesLivres;
    }

    @RequestMapping(value = "/AddLivre", method = RequestMethod.POST)
    public ModelAndView addLivre(livre newLivre) {

        ILivreDAO livreDAO = new LivreDAO();
        livreDAO.addLivre(newLivre);

        return new ModelAndView("redirect:/Admin/ListeLivres");
    }

    @RequestMapping(value = "/AlterLivre", method = RequestMethod.POST)
    public ModelAndView alterLivre(livre newLivre) {

        ILivreDAO livreDAO = new LivreDAO();
        livreDAO.alterLivre(newLivre);

        return new ModelAndView("redirect:/Admin/ListeLivres");
    }

    @RequestMapping(value = "/Signalement", method = RequestMethod.GET)
    public ModelAndView signalementList(HttpServletRequest request) {

        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        ModelAndView viewMesLivres = new ModelAndView("Admin/ListeSignal");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        ISignalementDAO signalementDAO = new SignalementDAO();
        List<signalement> signalementList = signalementDAO.getAllSignal();

        viewMesLivres.addObject("membre", membre);
        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("signalementList", signalementList);

        return viewMesLivres;
    }

    @RequestMapping(value = "/Regler/Signalement", method = RequestMethod.POST)
    public ModelAndView signalementRegler(@RequestParam("idSignal") int idSignal) {
        ISignalementDAO signalementDAO = new SignalementDAO();
        signalementDAO.verifierSignalement(idSignal);
        return new ModelAndView("redirect:/Admin/Signalement");
    }

    @RequestMapping(value = "/Evaluations", method = RequestMethod.GET)
    public ModelAndView evaluationList(HttpServletRequest request) {
        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        ModelAndView viewMesLivres = new ModelAndView("Admin/ListeEvaluations");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        IEvaluationDAO evaluationDAO = new EvaluationDAO();
        List<avis> avisList = evaluationDAO.getAllAvis();

        viewMesLivres.addObject("membre", membre);
        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("avisList", avisList);

        return viewMesLivres;
    }

    @RequestMapping(value = "/Evaluations/Supprimer/{idAvis}", method = RequestMethod.GET)
    public ModelAndView evaluationList(@PathVariable int idAvis) {

        IEvaluationDAO evaluationDAO = new EvaluationDAO();
        evaluationDAO.deleteAvis(idAvis);
        return new ModelAndView("redirect:/Admin/Evaluations");

    }

    @RequestMapping(value = "/DemandeGestion", method = RequestMethod.GET)
    public ModelAndView demandeGestionList(HttpServletRequest request) {
        String login = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("login")) {
                login = c.getValue();
            }
        }
        ModelAndView viewMesLivres = new ModelAndView("Admin/ListeDemandeGestion");

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        IDemandeGestionDAO demandeGestionDAO = new DemandeGestionDAO();
        List<demandeGestion> demandeList = demandeGestionDAO.getAllDemande();

        viewMesLivres.addObject("membre", membre);
        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("demandeList", demandeList);

        return viewMesLivres;
    }

    @RequestMapping(value = "/DemandeGestion/{idDemande}/{etat}/{admin}", method = RequestMethod.GET)
    public ModelAndView demandeGestionReponse(@PathVariable int idDemande, @PathVariable String etat, @PathVariable String admin) {

        IDemandeGestionDAO demandeGestionDAO = new DemandeGestionDAO();
        demandeGestionDAO.repondreDemande(idDemande,etat,admin);
        return new ModelAndView("redirect:/Admin/DemandeGestion");

    }
}
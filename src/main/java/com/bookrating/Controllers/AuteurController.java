package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

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
        List<livre> livresAGerer = auteurDAO.getlivreGererByAuteur(login);

        IMembreDAO membreDAO = new MembreDAO();
        membre membre = membreDAO.getMembre(login);

        viewMesLivres.addObject("membre", membre);
        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("livres", livres);
        viewMesLivres.addObject("livresAGerer", livresAGerer);

        return viewMesLivres;
    }

    @RequestMapping(value = "/AddLivre", method = RequestMethod.POST)
    public ModelAndView addLivre(livre newLivre) {

        IAuteurDAO auteurDAO = new AuteurDAO();
        String nomAuteur = auteurDAO.getNomCompletAuteur(newLivre.getAddBy());
        newLivre.setNomAuteur(nomAuteur);
        ILivreDAO livreDAO = new LivreDAO();
        livreDAO.addLivre(newLivre);

        return new ModelAndView("redirect:/Auteur/MesLivres");
    }

    @RequestMapping(value = "/AlterLivre", method = RequestMethod.POST)
    public ModelAndView alterLivre(livre newLivre) {

        ILivreDAO livreDAO = new LivreDAO();
        livreDAO.alterLivre(newLivre);

        return new ModelAndView("redirect:/Auteur/MesLivres");
    }


    @RequestMapping(value = "/Livre/{idLivre}/Avis", method = RequestMethod.GET)
    public ModelAndView avisLivre(@PathVariable int idLivre, HttpServletRequest request) {

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

        ILivreDAO livreDAO = new LivreDAO();
        livre livreDetail = livreDAO.getLivre(idLivre);

        ICatLivresDAO catLivresDAO = new CatLivresDAO();
        List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        List<categorieEvaluation> catEvaluationList = catEvaluationDAO.getAllCategoriesEvaluation();

        IEvaluationDAO evaluationDAO = new EvaluationDAO();
        Map<String, Double> moyenneEvaluation = evaluationDAO.moyenNoteByEvaluation(idLivre);

        DecimalFormat df = new DecimalFormat("#.#");
        String moyenneNote = df.format(evaluationDAO.moyenNoteByLivre(idLivre));

        int nombreAvis = evaluationDAO.nbAvisByLivre(idLivre);

        IAuteurDAO auteurDAO = new AuteurDAO();
        List<AvisEvaluation> listAvis = auteurDAO.avisLivreAuteur(idLivre);

        membre membre = membreDAO.getMembre(login);

        ModelAndView avisLivreView = new ModelAndView("Auteur/AvisParLivre");
        avisLivreView.addObject("livreDetail", livreDetail);
        avisLivreView.addObject("login", login);
        avisLivreView.addObject("role", role);
        avisLivreView.addObject("catEvaluationList", catEvaluationList);
        avisLivreView.addObject("catLivreList", catLivreList);
        avisLivreView.addObject("moyenneEvaluation", moyenneEvaluation);
        avisLivreView.addObject("moyenneNote", moyenneNote);
        avisLivreView.addObject("nombreAvis", nombreAvis);
        avisLivreView.addObject("listAvis", listAvis);
        avisLivreView.addObject("membre", membre);
        return avisLivreView;
    }

    @RequestMapping(value = "/DemandeGestion", method = RequestMethod.POST)
    public ModelAndView DemandeGestionByAuteur(demandeGestion demande) {

        IDemandeGestionDAO demandeGestionDAO = new DemandeGestionDAO();
        demande.setEtatDemande("En cours");
        demandeGestionDAO.faireDemande(demande);

        return new ModelAndView("redirect:/Livres/Detail/" + demande.getIdLivre());
    }
}
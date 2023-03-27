package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Evaluation")
public class EvaluationController {

//    @Autowired
//    private EmailService sendMail;

    @RequestMapping(value = "/Add", method = RequestMethod.POST)
    public ModelAndView addAvis(@RequestParam Map<String, Integer> evaluationData, @RequestParam("commentaire") String commentaire,
                                @RequestParam("login") String login, @RequestParam("idLivre") int idLivre) {

        IEvaluationDAO evaluationDAO = new EvaluationDAO();
        //ajouter d'abord un avis :
        avis newAvis = new avis();
        newAvis.setCommentaire(commentaire);
        newAvis.setIdLivre(idLivre);
        newAvis.setLogin(login);
        evaluationDAO.addAvis(newAvis);
        // recupère le dernier id avis insert in database :
        int idLastAvis = evaluationDAO.getLastIdAvis();
        // ajouter la liste des évaluations à idLastAvis :
        for (Map.Entry<String, Integer> e : evaluationData.entrySet()) {
            if (!e.getKey().equals("commentaire") && !e.getKey().equals("login") && !e.getKey().equals("idLivre")) {
                evaluation newEvaluation = new evaluation(Integer.parseInt(String.valueOf(e.getValue())), Integer.parseInt(e.getKey()), idLastAvis);
                evaluationDAO.addEvaluationByAvis(newEvaluation);
            }
        }
        return new ModelAndView("redirect:/Livres/Detail/" + idLivre); // rediriger vers la page Détail livre

    }

    @RequestMapping(value = "/Signaler/{idLivre}", method = RequestMethod.POST)
    public ModelAndView signaleCommentaire(signalement newSignalement, @PathVariable int idLivre) {

        // sendMail.sendMail("marwa.zayane88@gmail.com","signaler l'avis ID:");
        ISignalementDAO signalementDAO = new SignalementDAO();
        signalementDAO.addSignal(newSignalement);
        return new ModelAndView("redirect:/Livres/Detail/" + idLivre); // rediriger vers la page Détail livre
    }

    @RequestMapping(value = "/Liste", method = RequestMethod.GET)
    public ModelAndView mesEvaluations(HttpServletRequest request) {

        String login = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("login")) {
                    login = c.getValue();
                }
            }
            IEvaluationDAO evaluationDAO = new EvaluationDAO();
            List<AvisEvaluation> avisEvaluationList = evaluationDAO.getAllEvaluationByMember(login);
            List<livre> livres = evaluationDAO.getAllLivreEvaluerByMember(login);

            ICatLivresDAO catLivresDAO = new CatLivresDAO();
            List<categorieLivre> catLivreList = catLivresDAO.listCatLivres();

            ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
            List<categorieEvaluation> catEvaluationList = catEvaluationDAO.getAllCategoriesEvaluation();

            ModelAndView mesEvaluationsView = new ModelAndView("Membre/MesEvaluations");
            mesEvaluationsView.addObject("avisEvaluationList", avisEvaluationList);
            mesEvaluationsView.addObject("livres", livres);
            mesEvaluationsView.addObject("catLivreList", catLivreList);
            mesEvaluationsView.addObject("catEvaluationList", catEvaluationList);
            mesEvaluationsView.addObject("login", login);

            return mesEvaluationsView;
        }else {
            return new ModelAndView("redirect:/Home"); // rediriger vers la page Home
        }

    }


}
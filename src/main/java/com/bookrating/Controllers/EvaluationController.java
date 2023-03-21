package com.bookrating.Controllers;

import com.bookrating.EmailService;
import com.bookrating.Models.DAO.EvaluationDAO;
import com.bookrating.Models.DAO.IEvaluationDAO;
import com.bookrating.Models.DAO.ISignalementDAO;
import com.bookrating.Models.DAO.SignalementDAO;
import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;
import com.bookrating.Models.Entities.signalement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView signaleCommentaire(signalement newSignalement,@PathVariable int idLivre) {

       // sendMail.sendMail("marwa.zayane88@gmail.com","signaler l'avis ID:");
        ISignalementDAO signalementDAO = new SignalementDAO();
        signalementDAO.addSignal(newSignalement);
        return new ModelAndView("redirect:/Livres/Detail/" +idLivre); // rediriger vers la page Détail livre
    }


}
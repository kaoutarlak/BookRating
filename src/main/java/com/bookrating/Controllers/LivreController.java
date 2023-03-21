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

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

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

        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        List<categorieEvaluation> catEvaluationList = catEvaluationDAO.getAllCategoriesEvaluation();

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
        listeLivresModel.addObject("catEvaluationList", catEvaluationList);
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

        ICatEvaluationDAO catEvaluationDAO = new CatEvaluationDAO();
        List<categorieEvaluation> catEvaluationList = catEvaluationDAO.getAllCategoriesEvaluation();

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
        listeLivresLusModel.addObject("catEvaluationList", catEvaluationList);
        return listeLivresLusModel;
    }

    @RequestMapping(value = "/AddAvis", method = RequestMethod.POST)
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

    @RequestMapping(value = "/Detail/{idLivre}", method = RequestMethod.GET)
    public ModelAndView detailLivre(@PathVariable int idLivre,HttpServletRequest request ) {

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

        IEvaluationDAO evaluationDAO =new EvaluationDAO();
        Map<String, Double> moyenneEvaluation = evaluationDAO.moyenNoteByEvaluation(idLivre);

        DecimalFormat df = new DecimalFormat("#.##");
        String moyenneNote =df.format(evaluationDAO.moyenNoteByLivre(idLivre));

        int nombreAvis = evaluationDAO.nbAvisByLivre(idLivre);

        ModelAndView DetailLivreJSP = new ModelAndView("DetailLivre");
        DetailLivreJSP.addObject("livreDetail",livreDetail);
        DetailLivreJSP.addObject("login",login);
        DetailLivreJSP.addObject("role",role);
        DetailLivreJSP.addObject("catEvaluationList", catEvaluationList);
        DetailLivreJSP.addObject("catLivreList", catLivreList);
        DetailLivreJSP.addObject("moyenneEvaluation", moyenneEvaluation);
        DetailLivreJSP.addObject("moyenneNote", moyenneNote);
        DetailLivreJSP.addObject("nombreAvis", nombreAvis);

        return DetailLivreJSP;
    }
}
package com.bookrating.Controllers;

import com.bookrating.Models.DAO.*;
import com.bookrating.Models.Entities.categorieLivre;
import com.bookrating.Models.Entities.livre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

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

        viewMesLivres.addObject("catLivreList", catLivreList);
        viewMesLivres.addObject("login", login);
        viewMesLivres.addObject("livres", livres);

        return viewMesLivres;
    }

//    @RequestMapping(value = "/AddLivre", method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ModelAndView addLivre(livre newLivre, @RequestParam("file") MultipartFile imageFile) throws IOException {
//        //String imageName = imageFile.getOriginalFilename();
//        //Path path = Paths.get("images/" + imageName);
//        //Files.write(path, imageFile.getBytes());
//
////        String uploadDir = "src/main/webapp/resources/images";
////        String imageName = imageFile.getOriginalFilename();
////        Path uploadPath = Paths.get(uploadDir);
////        if (!Files.exists(uploadPath)) {
////            Files.createDirectories(uploadPath);
////        }
////        try (InputStream inputStream = imageFile.getInputStream()) {
////            Path filePath = uploadPath.resolve(imageName);
////            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
////        } catch (IOException ioe) {
////            throw new IOException("Could not save image file: " + imageName, ioe);
////        }
//        //return new ModelAndView("redirect:/Auteur/MesLivres");
//
//    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public ModelAndView upload(HttpServletRequest request, livre newLivre) throws ServletException, IOException {

        Part filePart = request.getPart("file"); // Récupère l'élément <input type="file" name="file">
        System.out.println("file = " + filePart);
//        System.out.println("filePart = " + filePart);
//        String fileName = filePart.getSubmittedFileName();
//        InputStream fileContent = filePart.getInputStream();
//
//        // Crée un nouveau fichier dans le répertoire /images de votre application
//        String applicationPath = request.getServletContext().getRealPath("");
//        String imagesPath = applicationPath + "images/";
//        File file = new File(imagesPath + fileName);
//
//        // Écrit le contenu du fichier téléchargé dans le nouveau fichier créé
//        try (OutputStream out = new FileOutputStream(file)) {
//            int read;
//            final byte[] bytes = new byte[1024];
//            while ((read = fileContent.read(bytes)) != -1) {
//                out.write(bytes, 0, read);
//            }
//        }

        return new ModelAndView("redirect:/Auteur/MesLivres");
    }

    @RequestMapping(value = "/AddLivre", method = RequestMethod.POST)
    public ModelAndView addLivre( livre newLivre)  {

        IAuteurDAO auteurDAO = new AuteurDAO();
        String nomAuteur = auteurDAO.getNomCompletAuteur(newLivre.getAddBy());
        newLivre.setNomAuteur(nomAuteur);
        ILivreDAO livreDAO = new LivreDAO();
        livreDAO.addLivre(newLivre);

        return new ModelAndView("redirect:/Auteur/MesLivres");
    }

}
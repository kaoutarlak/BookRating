package com.bookrating.Models.DAO;


import com.bookrating.Models.Entities.categorieLivre;

import java.util.List;

public interface ILivreLuDAO {

    void establichConnection();

    void closeConnection();

    void addLivreLu(String login,int idLivre);

    List<categorieLivre> getAllLivreLu();


}

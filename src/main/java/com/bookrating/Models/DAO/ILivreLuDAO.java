package com.bookrating.Models.DAO;


import com.bookrating.Models.Entities.livre;

import java.util.List;

public interface ILivreLuDAO {

    void establichConnection();

    void closeConnection();

    void addLivreLu(String login,int idLivre);

    List<livre> getAllLivreLu(String login);
    int nbPageLivreLu(String login);

}

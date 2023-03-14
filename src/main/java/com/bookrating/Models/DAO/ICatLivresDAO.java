package com.bookrating.Models.DAO;


import com.bookrating.Models.Entities.*;

import java.util.List;

public interface ICatLivresDAO {

    void establichConnection();

    void closeConnection();

    void addCategoryLivre(categorieLivre categorieLivre);

    List<categorieLivre> listCatLivres();

}

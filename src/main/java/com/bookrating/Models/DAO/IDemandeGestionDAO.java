package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.demandeGestion;

import java.util.List;

public interface IDemandeGestionDAO {

    void establichConnection();

    void closeConnection();

    List<demandeGestion> getAllDemande();

    void faireDemande(demandeGestion demande);

    void repondreDemande(int idDemande, String etatDemande);

}
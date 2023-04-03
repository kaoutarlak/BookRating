package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.signalement;

import java.util.List;

public interface ISignalementDAO {

    void establichConnection();

    void closeConnection();

    void addSignal(signalement newSignalement);

    List<signalement> getAllSignal();
    void verifierSignalement(int idSignal);

}
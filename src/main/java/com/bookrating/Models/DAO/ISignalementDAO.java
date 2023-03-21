package com.bookrating.Models.DAO;

import com.bookrating.Models.Entities.avis;
import com.bookrating.Models.Entities.evaluation;
import com.bookrating.Models.Entities.signalement;

import java.util.List;
import java.util.Map;

public interface ISignalementDAO {

    void establichConnection();

    void closeConnection();

    void addSignal(signalement newSignalement);

}
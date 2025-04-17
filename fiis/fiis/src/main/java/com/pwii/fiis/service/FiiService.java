package com.pwii.fiis.service;


import java.util.List;
import com.pwii.fiis.model.Fii;

public interface FiiService { //Interface comum
    List<Fii> getAllFiis(); //Ideal não é acessar repository direto
    void saveFii(Fii fii);
    Fii getFiiById(long id);
    void deleteFiiById(long id);
}


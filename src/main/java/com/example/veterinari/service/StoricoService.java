package com.example.veterinari.service;

import com.example.veterinari.model.Storico;

import java.time.LocalDateTime;
import java.util.List;


public interface StoricoService {

    List<Storico> elencoStorico();
    Storico datiStorico(int id);
    void aggiuntaStorico(Storico storico, int idAnimale);
    List<Storico> elencoStoricoPerAnimale(Integer idAnimale);
    void eliminaStorico(int id);
}



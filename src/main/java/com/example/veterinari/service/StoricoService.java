package com.example.veterinari.service;

import com.example.veterinari.model.Storico;

import java.time.LocalDateTime;
import java.util.List;


public interface StoricoService {

    List<Storico> elencoStorico();
    Storico datiStorico(int id);
    void aggiuntaStorico(Storico storico);
    // void modificaDatiStorico(int id, int idAnimale, String tipo, String nome, String dosaggioDettaglio, LocalDateTime dataOraPrestazione, LocalDateTime dataOraRichiamo, String note);
    boolean eliminazioneStorico(int id);
    List<Storico> elencoStoricoPerAnimale(Integer idAnimale);
    }



package com.example.veterinari.service;

import com.example.veterinari.model.Storico;

import java.time.LocalDateTime;
import java.util.List;


public interface StoricoService {

    List<Storico> elencoStorico();
    Storico datiStorico(int id);
    boolean aggiuntaStorico(Storico storico, int idAnimale, String tipo, String nome, String dosaggioDettaglio, LocalDateTime dataOraPrestazione, LocalDateTime dataOraRichiamo, String note);
    boolean eliminazioneStorico(int id);

    }



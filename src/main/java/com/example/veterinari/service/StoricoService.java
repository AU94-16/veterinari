package com.example.veterinari.service;

import com.example.veterinari.model.Storico;

import java.time.LocalDateTime;
import java.util.List;


public interface StoricoService {

    List<Storico> elencoStorico();
    Storico datiStorico(int id);
    boolean aggiuntaStorico(Storico storico, int idAnimale, String tipo, String nome, String dosaggio_dettaglio, LocalDateTime data_ora_prestazione, LocalDateTime data_ora_richiamo, String note);
    boolean eliminazioneStorico(int id);

    }



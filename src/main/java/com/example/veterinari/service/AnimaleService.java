package com.example.veterinari.service;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Veterinario;
import org.springframework.web.multipart.MultipartFile;


import java.time.Year;
import java.util.List;


public interface AnimaleService {
    // metodo per ottenere la lista degli animali registrati dal DB (nessun argomento da passare perchè si fa una lettura generale per fornire tutta la lista
    List<Animale> elencoAnimali();

    // metodo per riportare un oggetto animale e lo chiamiamo datiAnimale(int id) perchè ricerchiamo ogni singolo animale per id e creiamo una scheda di dettaglio
    Animale datiAnimale(int id);

    // metodo per la registrazione di un animale a cui passiamo i dati recuperati tramite il form di registrazione dell'animale, metodo usato anche per la modifica dei campo 
    void registrazioneAnimale(Animale animale, String nome, String specie, String razza, char sesso, MultipartFile fotografia, Year annoDiNascita, char sterilizzato, String allergie, int idProprietario);

    // eliminazione di un animale dal registro del veterinario nell'area riservata
    void eliminazioneAnimale (int idAnimale);

    //metodo per ottenere la lista degli animali in base al veterinario
    List<Animale> elencoAnimaliVet(Veterinario veterinario);


}

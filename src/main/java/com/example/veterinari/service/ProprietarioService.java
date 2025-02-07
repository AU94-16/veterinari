package com.example.veterinari.service;

import com.example.veterinari.model.Proprietario;
import java.util.List;

public interface ProprietarioService {
    Proprietario inserisciProprietario(Proprietario proprietario);
    List<Proprietario> elencoProprietario();
    Proprietario datiProprietario(int id);
    void stampaProprietario();
}

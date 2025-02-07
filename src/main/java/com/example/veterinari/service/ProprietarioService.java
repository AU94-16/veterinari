package com.example.veterinari.service;

import com.example.veterinari.model.Proprietario;
import java.util.List;

public interface ProprietarioService {
    void inserisciProprietario(Proprietario proprietario);
    List<Proprietario> elencoProprietario();
    Proprietario datiProprietario(int id);
}

package com.example.veterinari.service;

import com.example.veterinari.dao.ProprietarioDao;
import com.example.veterinari.model.Proprietario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioServiceImpl implements ProprietarioService {

    @Autowired
    private ProprietarioDao proprietarioDao;

    @Override
    public void inserisciProprietario(Proprietario proprietario) {
        System.out.println("Proprietario prima del salvataggio: " + proprietario); // Log
        proprietarioDao.save(proprietario);
        System.out.println("Proprietario salvato correttamente: " + proprietario); // Log
    }

    @Override
    public List<Proprietario> elencoProprietario() {
        return (List<Proprietario>) proprietarioDao.findAll();
    } // serve nella select per selezionare il proprietario quando si registra un nuovo animale (se il proprietario ha più animali)

    @Override
    public Proprietario datiProprietario(int id) {
        return proprietarioDao.findById(id).get();
    }


}

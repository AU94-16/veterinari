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
        proprietarioDao.save(proprietario);
    }

    @Override
    public List<Proprietario> elencoProprietario() {
        return (List<Proprietario>) proprietarioDao.findAll();
    }

    @Override
    public Proprietario datiProprietario(int id) {
        return proprietarioDao.findById(id).get();
    }

}

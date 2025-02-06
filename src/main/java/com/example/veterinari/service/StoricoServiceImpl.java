package com.example.veterinari.service;

import com.example.veterinari.dao.StoricoDao;
import com.example.veterinari.model.Storico;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoricoServiceImpl implements StoricoService {

    @Autowired
    private StoricoDao storicoDao;


    @Override
    public List<Storico> elencoStorico() {
        return List.of();
    }
}

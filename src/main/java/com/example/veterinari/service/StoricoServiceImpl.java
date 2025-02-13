package com.example.veterinari.service;

import com.example.veterinari.dao.AnimaleDao;
import com.example.veterinari.dao.StoricoDao;
import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Storico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StoricoServiceImpl implements StoricoService {

    @Autowired
    private StoricoDao storicoDao;

    @Autowired
    private AnimaleDao animaleDao;
    @Autowired
    private AnimaleService animaleService;

    @Override
    public List<Storico> elencoStorico() {

        return (List<Storico>) storicoDao.findAll();
    }

    @Override
    public Storico datiStorico(int id) {
        Optional<Storico> storicoOptional = storicoDao.findById(id);
        if (storicoOptional.isPresent())
            return storicoOptional.get();
        return null;

    }

    @Override
    public void aggiuntaStorico(Storico storico, int animaleId) {
        Animale animale = animaleDao.findById(animaleId).get();

        storico.setAnimale(animale);
        storicoDao.save(storico);
    }




    @Override
    public List<Storico> elencoStoricoPerAnimale(Integer idAnimale) {
        return storicoDao.findStoricoByAnimale_Id(idAnimale);
    }

    @Override
    public void eliminaStorico(int id) {
        Storico storico = datiStorico(id);
        storicoDao.delete(storico);
    }
}

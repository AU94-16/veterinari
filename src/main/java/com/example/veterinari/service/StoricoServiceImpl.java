package com.example.veterinari.service;

import com.example.veterinari.dao.StoricoDao;
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
    public void aggiuntaStorico(Storico storico) {
        storicoDao.save(storico);
    }


    @Override
    public boolean eliminazioneStorico(int id) {
        Optional<Storico> storico = storicoDao.findById(id);

        if (storico.isPresent()) {
            storicoDao.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Storico> elencoStoricoPerAnimale(Integer idAnimale) {
        return storicoDao.findStoricoByAnimale_Id(idAnimale);
    }
}

package com.example.veterinari.service;

import com.example.veterinari.dao.StoricoDao;
import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Storico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return null;
    }

    @Override
    public boolean aggiuntaStorico(Storico storico, int idAnimale, String tipo, String nome, String dosaggio_dettaglio, LocalDateTime data_ora_prestazione, LocalDateTime data_ora_richiamo, String note) {
        Animale animale = animaleService.datiAnimale(idAnimale);
        if (animale == null) {
            return false;
        }

        storico.setAnimale(animale);
        storico.setTipo(tipo);
        storico.setNome(nome);
        storico.setDosaggioDettaglio(dosaggio_dettaglio);
        storico.setDataOraPrestazione(data_ora_prestazione);
        storico.setDataOraRichiamo(data_ora_richiamo);
        storico.setNote(note);

        storicoDao.save(storico);
        return true;
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
}

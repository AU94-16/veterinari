package com.example.veterinari.dao;

import com.example.veterinari.model.Storico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoricoDao extends CrudRepository<Storico, Integer> {

    List<Storico> findStoricoByAnimale_Id(Integer idAnimale);
}

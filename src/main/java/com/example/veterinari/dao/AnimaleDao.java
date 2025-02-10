package com.example.veterinari.dao;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Veterinario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnimaleDao extends CrudRepository<Animale, Integer> {

    // Query per trovare gli animali associati a un determinato veterinario
    List<Animale> findByVeterinario_Id(Integer idVeterinario);
}

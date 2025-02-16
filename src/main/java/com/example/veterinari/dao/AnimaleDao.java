package com.example.veterinari.dao;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimaleDao extends CrudRepository<Animale, Integer> {

    // Query per trovare gli animali associati a un determinato veterinario
    List<Animale> findByVeterinario_Id(Integer idVeterinario);

    //ricerca animali in base al nome
    //@Query("SELECT a FROM Animale a WHERE a.nome LIKE %:nome% AND a.veterinario.id = :veterinarioId")
    List<Animale> findByNomeAndVeterinarioId(@Param("nome") String nome, @Param("veterinarioId") int veterinarioId);

    //ricerca animali in base alla specie
    //@Query("SELECT a FROM Animale a WHERE a.specie LIKE %:specie% AND a.veterinario.id = :veterinarioId")
    List<Animale> findBySpecieAndVeterinarioId(@Param("specie") String nome, @Param("veterinarioId") int veterinarioId);

    //ricerca animali in base al nome e cognome del proprietario
    @Query("SELECT a FROM Animale a WHERE a.proprietario.nomeProprietario LIKE %:nomeProprietario% AND a.veterinario.id = :veterinarioId")
    List<Animale> findByNomeProprietarioAndVeterinarioId(@Param("nomeProprietario") String nomeProprietario, @Param("veterinarioId") int veterinarioId);
}

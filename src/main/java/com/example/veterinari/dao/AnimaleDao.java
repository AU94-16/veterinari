package com.example.veterinari.dao;

import com.example.veterinari.model.Animale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimaleDao extends CrudRepository<Animale, Integer> {

    // Query per trovare gli animali associati a un determinato veterinario
    List<Animale> findByVeterinario_Id(Integer idVeterinario);

    //ricerca animali in base al nome
    List<Animale> findByNome(String nome);

    //ricerca animali in base alla specie
    List<Animale> findBySpecie(String specie);

    //ricerca animali in base al nome e cognome del proprietario
    /*@Query("SELECT * FROM Animale a JOIN proprietario p ON a.id_proprietario = p.id " +
            "WHERE LOWER(a.proprietario.nome) LIKE LOWER(CONCAT('%', :nome, '%')) " +
            "OR LOWER(a.proprietario.cognome) LIKE LOWER(CONCAT('%', :cognome, '%'))")*/
    @Query("SELECT a FROM Animale a WHERE LOWER(a.proprietario.nome) LIKE LOWER(CONCAT('%', :nome, '%')) " +
            "OR LOWER(a.proprietario.cognome) LIKE LOWER(CONCAT('%', :cognome, '%'))")
    List<Animale> findByProprietario(@Param("nome") String nome, @Param("cognome") String cognome);
}

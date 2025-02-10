package com.example.veterinari.dao;

import com.example.veterinari.model.Veterinario;
import org.springframework.data.repository.CrudRepository;

public interface VeterinarioDao extends CrudRepository<Veterinario, Integer> {

    Veterinario findByEmailAndPassword(String email, String password);
   Veterinario findByEmail(String email);
}

package com.example.veterinari.dao;

import com.example.veterinari.model.Animale;
import org.springframework.data.repository.CrudRepository;

public interface AnimaleDao extends CrudRepository<Animale, Integer> {
}

package com.example.veterinari.service;

import com.example.veterinari.model.Veterinario;
import jakarta.servlet.http.HttpSession;

public interface VeterinarioService {

    boolean loginVeterinario(String email, String password, HttpSession session);
    void registrazioneVeterinario(Veterinario veterinario);
    boolean controlloEmail(String email);
}

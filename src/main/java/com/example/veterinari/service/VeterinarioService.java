package com.example.veterinari.service;

import com.example.veterinari.model.Veterinario;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public interface VeterinarioService {

    Veterinario datiVeterinario(int id);
    boolean loginVeterinario(String email, String password, HttpSession session);
    void registrazioneVeterinario(Veterinario veterinario);
    boolean controlloEmail(String email);
    void modificaDatiVeterinario(int id, String telefono, String citta, MultipartFile fotoProfilo, HttpSession session);
}

package com.example.veterinari.service;

import com.example.veterinari.dao.VeterinarioDao;
import com.example.veterinari.model.Veterinario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

public class VeterinarioServiceImpl implements VeterinarioService{

   @Autowired
   private VeterinarioDao veterinarioDao;

    @Override
    public boolean loginVeterinario(String email, String password, HttpSession session) {
        Veterinario veterinario = veterinarioDao.findByEmailAndPassword(email, password);

        if(veterinario != null) {
            session.setAttribute("veterinario", veterinario);
            return true;
        }

        return false;
    }

    @Override
    public void registrazioneVeterinario(Veterinario veterinario) {
            veterinarioDao.save(veterinario);
    }

    @Override
    public boolean controlloEmail(String email) {
        return veterinarioDao.findByEmail(email) == null;
    }
}

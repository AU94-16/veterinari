package com.example.veterinari.service;

import com.example.veterinari.dao.VeterinarioDao;
import com.example.veterinari.model.Veterinario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

   @Autowired
   private VeterinarioDao veterinarioDao;

   //Recupera
    @Override
    public Veterinario datiVeterinario(int id) {
       Optional<Veterinario> veterinarioOptional = veterinarioDao.findById(id); //optional: pensato per gestire id che non ci sono
        if(veterinarioOptional.isPresent())
            return veterinarioOptional.get();
        return null;
    }

    //Login del Veterinario
    @Override
    public boolean loginVeterinario(String email, String password, HttpSession session) {
        Veterinario veterinario = veterinarioDao.findByEmailAndPassword(email, password);

        if(veterinario != null) {
            session.setAttribute("veterinario", veterinario);
            return true;
        }
        return false;
    }

    //Registrazione nuovo veterinario
    @Override
    public void registrazioneVeterinario(Veterinario veterinario) {
        veterinarioDao.save(veterinario);
    }

    //Controllo email per registrazione
    @Override
    public boolean controlloEmail(String email) {
        return veterinarioDao.findByEmail(email) == null;
    }

    //Modifica dati Veterinario
    @Override
    public void modificaDatiVeterinario(int id, String telefono, String citta, MultipartFile fotoProfilo, HttpSession session) {
        // Recupera il veterinario dal database
        Veterinario veterinario = veterinarioDao.findById(id).get();

            veterinario.setTelefono(telefono);
            veterinario.setCitta(citta);

            if (fotoProfilo != null && !fotoProfilo.isEmpty()) {
                try {
                    String formato = fotoProfilo.getContentType();
                    String fotoProf = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(fotoProfilo.getBytes());
                    veterinario.setFotoProfilo(fotoProf);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }  // Modifica il campo FotoProfilo
            }

            veterinarioDao.save(veterinario);
            session.setAttribute("veterinario", veterinario);

    }
}

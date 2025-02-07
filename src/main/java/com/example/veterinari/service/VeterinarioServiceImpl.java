package com.example.veterinari.service;

import com.example.veterinari.dao.VeterinarioDao;
import com.example.veterinari.model.Veterinario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

   @Autowired
   private VeterinarioDao veterinarioDao;

    @Override
    public Veterinario datiVeterinario(int id) {
        return veterinarioDao.findById(id).get();
    }

    @Override
    public boolean loginVeterinario(String email, String password, HttpSession session) {
        Veterinario veterinario = veterinarioDao.findByEmailAndPassword(email, password);

        if(veterinario != null) {
            session.setAttribute("veterinario", veterinario);
            return true;
        }

        return false;
    }

    //metodo usato per registrazione - salvare le modifiche - formato foto
    @Override
    public void registrazioneVeterinario(Veterinario veterinario) {
        veterinarioDao.save(veterinario);
    }


    @Override
    public boolean controlloEmail(String email) {

        return veterinarioDao.findByEmail(email) == null;
    }

    @Override
    public void modificaDatiVeterinario(int id, String telefono, String citta, MultipartFile fotoProfilo) {
        // Recupera il veterinario dal database usando l'ID
        Veterinario veterinario = veterinarioDao.findById(id).orElse(null);

        if (telefono != null && !telefono.isEmpty()) {
            veterinario.setTelefono(telefono);  // Aggiorna il campo Telefono
        }
        if (citta != null && !citta.isEmpty()) {
            veterinario.setCitta(citta);  // Aggiorna il campo Citta
        }
        if (fotoProfilo != null && !fotoProfilo.isEmpty()) {
            try {
                String formato = fotoProfilo.getContentType();
                String fotoProf = "data" + formato + ";base64" + Base64.getEncoder().encodeToString(fotoProfilo.getBytes());
                veterinario.setFotoProfilo(fotoProf);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }  // Aggiorna il campo FotoProfilo
        }

        veterinarioDao.save(veterinario);


    }
}

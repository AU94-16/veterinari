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
    public void registrazioneVeterinario(Veterinario veterinario, String telefono, String citta, MultipartFile fotoProfilo) {
        veterinario.setTelefono(telefono);
        veterinario.setCitta(citta);
        if(fotoProfilo != null && !fotoProfilo.isEmpty()) {
            try {
                String formato = fotoProfilo.getContentType();
                String fotoProf = "data" + formato + ";base64" + Base64.getEncoder().encodeToString(fotoProfilo.getBytes());
                veterinario.setFotoProfilo(fotoProf);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        veterinarioDao.save(veterinario);
    }

    @Override
    public boolean controlloEmail(String email) {

        return veterinarioDao.findByEmail(email) == null;
    }
}

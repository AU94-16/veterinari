package com.example.veterinari.service;

import com.example.veterinari.dao.AnimaleDao;
import com.example.veterinari.dao.ProprietarioDao;
import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Veterinario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.time.Year;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class AnimaleServiceImpl implements AnimaleService {

    // mi serve l'aggancio con Animale dao per il metodo findAll() del crudRepository
    // il metodo elencoAnimali() ritornerà AnimaleDao.findAll()
    @Autowired
    private AnimaleDao animaleDao;

    @Autowired
    private ProprietarioDao proprietarioDao;

    @Autowired
    private ProprietarioService proprietarioService;

    @Override
    public List<Animale> elencoAnimali() {
        return (List<Animale>) animaleDao.findAll();
    }

    @Override
    public Animale datiAnimale(int id) {
        Optional<Animale> animaleOptional = animaleDao.findById(id);
        if (animaleOptional.isPresent())
            return animaleOptional.get();
        return null;
        // OPTIONAL = se nella scatola, che abbiamo chiamato con Optional, animale isPresenti allora lo tiriamo fuori e lo ritorniamo con return animaleOptional.get(), altrimenti se non c'è nulla return null
    }

    @Override
    public void registrazioneAnimale(Animale animale) {
        // per la fotografia dell'animale
       /* if(fotografia != null && !fotografia.isEmpty()) {
            try {
                String formato = fotografia.getContentType();
                String fotografiaCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(fotografia.getBytes());
                animale.setFotografia(fotografiaCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/
        System.out.println("Animale prima del salvataggio: " + animale);
        animaleDao.save(animale);
        System.out.println("Animale salvato correttamente: " + animale);
    }

    @Override
    public void modificaAnimale(Animale animale, String nome, String specie, String razza, char sesso, String peso, MultipartFile fotografia, Year annoDiNascita, String colore, char sterilizzato, String allergie) {
        animale.setNome(nome);
        animale.setSpecie(specie);
        animale.setRazza(razza);
        animale.setSesso(sesso);
        animale.setPeso(Float.parseFloat(peso));
        // per la fotografia dell'animale
        if(fotografia != null && !fotografia.isEmpty()) {
            try {
                String formato = fotografia.getContentType();
                String fotografiaCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(fotografia.getBytes());
                animale.setFotografia(fotografiaCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        animale.setAnnoDiNascita(annoDiNascita);
        animale.setColore(colore);
        animale.setSterilizzato(sterilizzato);
        animale.setAllergie(allergie);
        animaleDao.save(animale);
    }

    @Override
    public void eliminazioneAnimale(int idAnimale) {
        Animale animale = datiAnimale(idAnimale);
        animaleDao.delete(animale);
    }

    // Trova gli animali associati a un veterinario
    @Override
    public List<Animale> elencoAnimaliVet(int idVeterinario) {

        return animaleDao.findByVeterinario_Id(idVeterinario);
    }

    //Ricerca Animale per campo (select) con valore dato
    @Override
    public List<Animale> ricercaAnimale(int idVeterinario, String campo, String valore) {
        switch (campo) {
            case "nome":
                return animaleDao.findByNomeAndVeterinarioId(valore, idVeterinario);

            case "specie":
                return animaleDao.findBySpecieAndVeterinarioId(valore,idVeterinario);

            default: //case "proprietario"
                // Dividiamo il valore in nome e cognome (separati da spazio)

                /*String[] nomeCognome = valore.split(" ");
                String nome = nomeCognome[0];
                String cognome = nomeCognome.length > 1 ? nomeCognome[1] : "";*/

                return animaleDao.findByNomeProprietarioAndVeterinarioId(valore, idVeterinario);
        }

    }

}

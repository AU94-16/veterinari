package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/registrazione_animale")
public class RegistrazioneAnimaleController {


        @Autowired
        private AnimaleService animaleService;

        @Autowired
        private ProprietarioService proprietarioService;


        @GetMapping
        public String getPage(Model model, HttpSession session) {
            // Recupera il veterinario dalla sessione
            Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");

            // Controllo login
            if (veterinario == null) {
                return "redirect:/accedi";
            }

            // Aggiunge al modello un nuovo animale e un nuovo proprietario
            model.addAttribute("animale", new Animale());
            model.addAttribute("proprietario", new Proprietario());

            // Recupera la lista dei proprietari esistenti per Select
            model.addAttribute("proprietari", proprietarioService.elencoProprietario());
            return "registrazione_animale";
        }


    @PostMapping
    public String formManager(
            @Valid @ModelAttribute("animale") Animale animale,
            BindingResult animaleResult,
            @RequestParam MultipartFile fotografia,
            @RequestParam(value = "idProprietario", required = false) Integer id,
            @RequestParam(value = "nome") String nomeProprietario,
            @RequestParam String indirizzo,
            @RequestParam String citta,
            @RequestParam(value = "CAP") String cap,
            @RequestParam String codiceFiscale,
            @RequestParam String email,
            @RequestParam String telefono,
            HttpSession session, Model model) {

        Veterinario veterinarioSession = (Veterinario) session.getAttribute("veterinario");

     /*   if (animaleResult.hasErrors()) {
            model.addAttribute("proprietari", proprietarioService.elencoProprietario());
            return "registrazione_animale";
        }*/

        System.out.println("dati animale: " + animale);
        System.out.println("ID Proprietario esistente: " + id);


                //Gestione proprietario
        Proprietario proprietario;

        if (id != null && id > 0) {
            // Proprietario esistente
            System.out.println("prop esistente");
            proprietario = proprietarioService.datiProprietario(id);
            animale.setProprietario(proprietario);

        } else if (nomeProprietario != null && !nomeProprietario.isEmpty()) {
            // Nuovo proprietario
            System.out.println("prop nuovo");
            proprietario = new Proprietario();
            proprietario.setNome(nomeProprietario);
            proprietario.setIndirizzo(indirizzo);
            proprietario.setCitta(citta);
            proprietario.setCAP(cap);
            proprietario.setCodiceFiscale(codiceFiscale);
            proprietario.setEmail(email);
            proprietario.setTelefono(telefono);

            proprietarioService.inserisciProprietario(proprietario);
            animale.setProprietario(proprietario);
        } else {
            model.addAttribute("proprietari", proprietarioService.elencoProprietario());
            return "registrazione_animale";
        }

        animale.setVeterinario(veterinarioSession);
        animaleService.registrazioneAnimale(animale, fotografia);
        return "redirect:/area_riservata";
    }
}




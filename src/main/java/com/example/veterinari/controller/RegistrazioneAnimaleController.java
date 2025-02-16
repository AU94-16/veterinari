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

import java.util.Base64;

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
            @Valid @ModelAttribute("proprietario") Proprietario proprietarioNuovo,
            BindingResult proprietarioResult,
          /*  @RequestParam MultipartFile fotografia,*/
            @RequestParam(value = "idProprietario", required = false) Integer id,
            HttpSession session, Model model) {

        Veterinario veterinarioSession = (Veterinario) session.getAttribute("veterinario");

       /* animaleResult.rejectValue("fotografia", "null", "Foto non obbligatorio");*/

        // Controllo errori validazione
       if (animaleResult.hasErrors() && (proprietarioResult.hasErrors() || proprietarioNuovo.getNomeProprietario() != null )) {
           System.out.println("mex errore");
           System.out.println(animaleResult.toString());
           System.out.println(proprietarioResult.toString());
           model.addAttribute("proprietari", proprietarioService.elencoProprietario());
            return "registrazione_animale";
        }

       Proprietario proprietario;

        // Se esiste un proprietario, lo recuperiamo, altrimenti usiamo quello nuovo
        if (id != null && id > 0) {
            // Proprietario esistente
            System.out.println("prop esistente");
            proprietario = proprietarioService.datiProprietario(id);
        } else {
            // Nuovo proprietario
            System.out.println("prop nuovo");
            proprietario = proprietarioNuovo;
            proprietarioService.inserisciProprietario(proprietario);
        }

        animale.setProprietario(proprietario);
        animale.setVeterinario(veterinarioSession);
        animaleService.registrazioneAnimale(animale);

        return "redirect:/area_riservata";
    }

}




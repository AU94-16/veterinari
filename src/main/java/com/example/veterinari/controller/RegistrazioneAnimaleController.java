package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import jakarta.persistence.Table;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registrazioneAnimale")
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
                return "redirect:/accedi"; // Reindirizza se non loggato
            }

            // Aggiunge al modello un nuovo animale e un nuovo proprietario
            model.addAttribute("animale", new Animale());
            model.addAttribute("proprietario", new Proprietario());

            // Recupera la lista dei proprietari esistenti per la selezione
            model.addAttribute("proprietari", proprietarioService.elencoProprietario());
            return "registrazioneAnimale";
        }


        @PostMapping
        public String formManager(@Valid @ModelAttribute Animale animale,
                                  BindingResult animaleResult,
                                  @RequestParam(value = "proprietarioEsistente", required = false) Integer idProprietario,
                                  @Valid @ModelAttribute Proprietario proprietario,
                                  BindingResult proprietarioResult,
                                  HttpSession session, Model model) {

            // Recupera il veterinario dalla sessione
            Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");
            if (veterinario == null) {
                return "redirect:/accedi";
            }

            // Se ci sono errori di validazione, torna al form con i messaggi di errore + riprende elenco proprietari
            if (animaleResult.hasErrors() || (idProprietario == null && proprietarioResult.hasErrors())) {
                model.addAttribute("proprietari", proprietarioService.elencoProprietario());
                return "aggiunta";
            }

            // Se è stato selezionato un proprietario esistente - associa proprietario e salva
            if (idProprietario != null && idProprietario > 0) {
                Proprietario proprietarioEsistente = proprietarioService.datiProprietario(idProprietario);
                animale.setProprietario(proprietarioEsistente);
            } else {
                // Se viene inserito un nuovo proprietario - associa proprietario e salva
                proprietarioService.inserisciProprietario(proprietario);
                animale.setProprietario(proprietario);
            }


            // Associa il veterinario e salva
            animale.setVeterinario(veterinario);
            animaleService.registrazioneAnimale(animale);
            return "redirect:/area_riservata";
        }
    }



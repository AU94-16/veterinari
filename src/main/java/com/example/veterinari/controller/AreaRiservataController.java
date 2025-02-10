package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;

import java.util.List;

@Controller
@RequestMapping("/area_riservata")
public class AreaRiservataController {

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private AnimaleService animaleService;

    @Autowired
    private ProprietarioService proprietarioService;


    //getPage + redirector a login in caso di mancato login + stampa animali per veterinario
    @GetMapping
    public String getPage(HttpSession session, Model model) {

       //controllo per accesso pagina senza login
        if (session.getAttribute("veterinario") == null) {
            return "redirect:/accedi";
        }
        //Recupera il veterinario dalla sessione
        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");

        // Recupera gli animali in cura
        List<Animale> animali = animaleService.elencoAnimaliVet(veterinario.getId());

        model.addAttribute("veterinario", veterinario);
        model.addAttribute("animali", animali);
        return "area_riservata";
    }

    //logout
    @GetMapping("/logout")
    public String logoutVeterinario(HttpSession session) {
        session.removeAttribute("veterinario");
        return "redirect:/";
    }


    //Registrazione animale
    @PostMapping("/registrazioneAnimale")
    public String formManager(@RequestParam String nome,
                              @RequestParam String specie,
                              @RequestParam String razza,
                              @RequestParam char sesso,
                              @RequestParam MultipartFile fotografia,
                              @RequestParam Year annoDiNascita,
                              @RequestParam char sterilizzato,
                              @RequestParam String allergie,
                              @RequestParam int idProprietario) {
        Animale animale = new Animale();
        animaleService.registrazioneAnimale(animale, nome, specie, razza, sesso, fotografia, annoDiNascita, sterilizzato, allergie, idProprietario);
        return "redirect:/";
    }

    @PostMapping("/registrazioneProprietario")
    public  String formManager(@ModelAttribute Proprietario proprietario) {
        proprietarioService.inserisciProprietario(proprietario);
        return "redirect:/";
    }

    //Ricerca
    @GetMapping("/ricerca")
    public List<Animale> ricercaAnimale(@RequestParam String campo,
                                        @RequestParam String valore){
        return animaleService.ricercaAnimale(campo, valore);
    }

}

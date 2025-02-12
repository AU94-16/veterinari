package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import com.example.veterinari.model.Storico;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import com.example.veterinari.service.StoricoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;
import java.util.List;


// localhost:8080/profilo_animale
@Controller
@RequestMapping ("/profilo_animale")
public class ProfiloAnimaleController {

    @Autowired
    private AnimaleService animaleService;

    @Autowired
    private StoricoService storicoService;

    @Autowired
    private ProprietarioService proprietarioService;


    @GetMapping
    public String getPage(@RequestParam int id,
                                   Model model,
                          HttpSession session) {

        // Controllo login veterinario
        if (session.getAttribute("veterinario") == null) {
            return "redirect:/accedi";
        }

        // Recupera animale
        Animale animale = animaleService.datiAnimale(id);

        // Recupera proprietario
        Proprietario proprietario = animale.getProprietario();

        // Recupera lo storico degli interventi
        List<Storico> storico = storicoService.elencoStoricoPerAnimale(id);

        model.addAttribute("animale", animale);
        model.addAttribute("storico", storico);
        model.addAttribute("proprietario", proprietario); // recupero anche del prorpietario insieme alla ricerca di animale
        return "profilo_animale";
    }

    //Modifica animale
    @PostMapping
    public String formManager(@RequestParam int id,
                              @RequestParam String nome,
                              @RequestParam String specie,
                              @RequestParam String razza,
                              @RequestParam char sesso,
                              @RequestParam String peso,
                              @RequestParam(required = false) MultipartFile fotografia,
                              @RequestParam Year annoDiNascita,
                              @RequestParam(required = false) String colore,
                              @RequestParam char sterilizzato,
                              @RequestParam(required = false) String allergie,
                              Model model) {
        Animale animale = animaleService.datiAnimale(id);
        animaleService.modificaAnimale(animale, nome, specie, razza, sesso, peso, fotografia, annoDiNascita, colore, sterilizzato, allergie);
        return "redirect:/profilo_animale?id=" + id;
    }



   /* @PostMapping("/registrazioneProprietario")
    public  String formManager(@ModelAttribute Proprietario proprietario) {
        proprietarioService.inserisciProprietario(proprietario);
        return "redirect:/";
    }*/

    @GetMapping("/eliminaAnimale")
    public String cancellazioneAnnimale(@RequestParam int id) {
        animaleService.eliminazioneAnimale(id);
        return "redirect:/profilo_animale";
    }

    @PostMapping("/aggiuntaStorico")
    public String formManager (@ModelAttribute Storico storico) { // in un secondo momento registriamo uno storico iniziale, per poi aggiungerne altri
        storicoService.aggiuntaStorico(storico);
                return "redirect:/profilo_animale";
    }


}
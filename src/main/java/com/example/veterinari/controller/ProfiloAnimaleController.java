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


// localhost:8080/animale
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
    public String dettaglioAnimale(int id, Model model, HttpSession session, int idStorico, int idProprietario) {
        Animale animale = animaleService.datiAnimale(id);

        model.addAttribute("animale", animale);
        model.addAttribute("storico", storicoService.datiStorico(idStorico));
        model.addAttribute("proprietario", proprietarioService.datiProprietario(idProprietario)); // recupero anche del prorpietario insieme alla ricerca di animale
        return "profilo_animale";
    }

    //Modifica animale
    @PostMapping("/modificaAnimale")
    public String formManager(@RequestParam int id,
                              @RequestParam String nome,
                              @RequestParam String specie,
                              @RequestParam String razza,
                              @RequestParam char sesso,
                              @RequestParam MultipartFile fotografia,
                              @RequestParam Year annoDiNascita,
                              @RequestParam char sterilizzato,
                              @RequestParam String allergie,
                              @RequestParam int idProprietario) {
        Animale animale = animaleService.datiAnimale(id);
        animaleService.registrazioneAnimale(animale, nome, specie, razza, sesso, fotografia, annoDiNascita, sterilizzato, allergie, idProprietario);
        return "redirect:/";
    }

    @PostMapping("/registrazioneProprietario")
    public  String formManager(@ModelAttribute Proprietario proprietario) {
        proprietarioService.inserisciProprietario(proprietario);
        return "redirect:/";
    }

    @GetMapping("/eliminaAnimale")
    public String cancellazioneAnnimale(@RequestParam int id) {
        animaleService.eliminazioneAnimale(id);
        return "redirect:/";
    }

    @PostMapping("/aggiuntaStorico")
    public String formManager (@ModelAttribute Storico storico) { // in un secondo momento registriamo uno storico iniziale, per poi aggiungerne altri
        storicoService.aggiuntaStorico(storico);
                return "redirect:/animale";
    }


}
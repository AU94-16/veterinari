package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;


@Controller
@RequestMapping("/Animale")
public class AnimaleController {


    @Autowired
    AnimaleService animaleService;

    @Autowired
    ProprietarioService proprietarioService;

    @GetMapping
    public String getPage(Model model) {
        Animale animale = new Animale();
        model.addAttribute("animale", animale);

        return "Animale";
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

}

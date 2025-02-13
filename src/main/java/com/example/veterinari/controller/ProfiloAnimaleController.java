package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Proprietario;
import com.example.veterinari.model.Storico;
import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import com.example.veterinari.service.StoricoService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("animale", animale);
        // Recupera proprietario
        Proprietario proprietario = proprietarioService.datiProprietario(id);
        model.addAttribute("proprietario", proprietario);
        // Recupera lista dello storico degli interventi
        List<Storico> storicoLista = storicoService.elencoStoricoPerAnimale(id);
        model.addAttribute("storicoLista", storicoLista);


        // Aggiungi l'oggetto storico vuoto per il form
        model.addAttribute("storico", new Storico());

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


    @GetMapping("/eliminaAnimale")
    public String cancellazioneAnnimale(@RequestParam int id) {
        animaleService.eliminazioneAnimale(id);
        return "redirect:/area_riservata";
    }



    @PostMapping("/aggiungiStorico")
    public String aggiungiStorico(@Valid @ModelAttribute("storico") Storico storico,
                                  BindingResult bindingResult,
                                  @RequestParam("animaleId") int animaleId,  // Usa @RequestParam per recuperare l'ID animale
                                  Model model) {

        if (bindingResult.hasErrors()) {
            return "profilo_animale";
        }

        storicoService.aggiuntaStorico(storico, animaleId);
        return "redirect:/profilo_animale?id=" + animaleId;
    }

    @GetMapping("/eliminaStorico")
    public String eliminaStorico(@RequestParam int storicoId, @RequestParam int animaleId) {
        storicoService.eliminaStorico(storicoId);
        return "redirect:/profilo_animale?id=" + animaleId;
    }

}
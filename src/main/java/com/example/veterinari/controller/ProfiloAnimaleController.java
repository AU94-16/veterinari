/*package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.StoricoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// localhost:8080/animale
@Controller
@RequestMapping ("/animale")
public class DettagliAnimaleController {

    @Autowired
    private AnimaleService animaleService;

    @Autowired
    private StoricoService storicoService;

    @GetMapping
    public String dettaglioAnimale(int id, Model model, HttpSession session) {
        Animale animale = animaleService.datiAnimale(id);

        model.addAttribute("animale", animale);
        model.addAttribute("storico", storicoService.elencoStorico());

        return "dettaglioAnimale";
    }
    @GetMapping
    public String modificaAnimale(int id, Model model){
        Animale animale = animaleService.datiAnimale(id);
        model.addAttribute("animale", animale);
        return "modificaAnimale";
    }
}
*/
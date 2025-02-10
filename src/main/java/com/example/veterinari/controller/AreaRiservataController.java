package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/area_riservata")
public class AreaRiservataController {

    @Autowired
    private VeterinarioService veterinarioService;

    @Autowired
    private AnimaleService animaleService;


    //getPage + redirector a login in caso di mancato login + stampa animali per veterinario
    @GetMapping
    public String getPage(HttpSession session, Model model) {

       //controllo per accesso pagina senza login
        if (session.getAttribute("veterinario") == null) {
            return "redirect:/accedi";
        }

        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");
        List<Animale> animali = animaleService.elencoAnimaliVet(veterinario);
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

    //metodo per aggiunta/modifica di alcuni campi di veterinario
    @PostMapping("/modificaDati")
    public String formManager(@RequestParam(required = false) MultipartFile fotoProfilo,
                              @RequestParam(required = false) String telefono,
                              @RequestParam(required = false) String citta,
                              HttpSession session) {

        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");
        veterinarioService.modificaDatiVeterinario(veterinario.getId(), telefono, citta, fotoProfilo);

        return "redirect:/area_riservata";
    }


}

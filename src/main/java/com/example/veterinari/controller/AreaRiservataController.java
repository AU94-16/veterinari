package com.example.veterinari.controller;

import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/areaRiservata")
public class AreaRiservataController {

    @Autowired
    private VeterinarioService veterinarioService;

    Veterinario veterinario;

    @GetMapping
    public String getPage(HttpSession session, Model model) {
        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");
        if (veterinario == null) {
            return "redirect:/login";
        }
        model.addAttribute("veterinario", veterinario);
        return "areaRiservata";
    }

    // Metodo per modificare i dati del veterinario
    @PostMapping("/modificaDati")
    public String modificaDatiVeterinario(@RequestParam(required = false) String telefono,
                                          @RequestParam(required = false) String citta,
                                          @RequestParam(required = false) MultipartFile fotoProfilo) {
        veterinarioService.registrazioneVeterinario(veterinario, telefono, citta, fotoProfilo);
        return "redirect:/areaRiservata";
    }
}

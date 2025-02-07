package com.example.veterinari.controller;

import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Controller
@RequestMapping("/areaRiservata")
public class AreaRiservataController {

    @Autowired
    private VeterinarioService veterinarioService;


    //getPage e redirector a login in caso di mancato login
    @GetMapping
    public String getPage(HttpSession session, Model model) {
        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");
        if (veterinario == null) {
            return "redirect:/login";
        }
        model.addAttribute("veterinario", veterinario);
        return "areaRiservata";
    }
    //logout
    @GetMapping("/logout")
    public String logoutVeterinario(HttpSession session) {
        session.removeAttribute("veterinario");
        return "redirect:/";
    }

    //metodo per modifica
    @PostMapping("/modificaDati")
    public String formManager(@RequestParam(required = false) MultipartFile fotoProfilo,
                              @RequestParam(required = false) String telefono,
                              @RequestParam(required = false) String citta,
                              HttpSession session) {

        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");
        veterinarioService.modificaDatiVeterinario(veterinario.getId(), telefono, citta, fotoProfilo);

        return "redirect:/areaRiservata";
    }


}

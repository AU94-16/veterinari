package com.example.veterinari.controller;

import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// localhost:8080/registrati
@Controller
@RequestMapping("/registrati")
public class RegistratiController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public String getPage(Model model) {
        Veterinario veterinario = new Veterinario();
        model.addAttribute("veterinario", veterinario);

        return "registrati";
    }

    //Registrazione Veterinario
    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Veterinario veterinario,
            BindingResult result,
            Model model) {
        if(result.hasErrors())
            return"registrati";
        //Controllo Email per duplicati
        if(!veterinarioService.controlloEmail(veterinario.getEmail())) {
            model.addAttribute("duplicato", "Email occupata");
            return "registrati";
        }
        veterinarioService.registrazioneVeterinario(veterinario);
        return "redirect:/accedi";
    }
}

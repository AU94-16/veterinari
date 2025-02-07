package com.example.veterinari.controller;

import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.VeterinarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// localhost:8080/registrazione
@Controller
@RequestMapping("/registrazione")
public class RegistrazioneController {

    @Autowired
    private VeterinarioService veterinarioService;

    Veterinario veterinario;

    @GetMapping
    public String getPage(Model model,
                          @RequestParam Integer id) {
        veterinario = veterinarioService.datiVeterinario(id);

        model.addAttribute("veterinario", veterinario);

        return "registrazione";
    }

    @PostMapping
    public String formManager(
            @Valid @ModelAttribute Veterinario veterinario,
            BindingResult result,
            Model model) {
        if(result.hasErrors())
            return"registrazione";

        if(!veterinarioService.controlloEmail(veterinario.getEmail())) {
            model.addAttribute("duplicato", "Email occupata");
            return "registrazione";
        }
        veterinarioService.registrazioneVeterinario(veterinario);
        return "redirect:/login";
    }
}

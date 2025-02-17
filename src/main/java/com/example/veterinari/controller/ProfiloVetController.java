package com.example.veterinari.controller;


import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;


@Controller
@RequestMapping("/profilo_vet")
public class ProfiloVetController {


    @Autowired
    private VeterinarioService veterinarioService;


    @GetMapping
    public String getPages(HttpSession session, Model model) {
        //Recupera il veterinario dalla sessione
        Veterinario veterinario = (Veterinario) session.getAttribute("veterinario");

        // Controllo login
        if (session.getAttribute("veterinario") == null) {
            return "redirect:/accedi";
        }
        model.addAttribute("veterinario", veterinario);

        return "profilo_vet";
    }

    //metodo per aggiunta/modifica di alcuni campi di veterinario
    @PostMapping("/modifica")
    public String formManager(@Valid @ModelAttribute Veterinario veterinario,
                              BindingResult result,
                              @RequestParam(required = false) MultipartFile fotoProfilo,
                              @RequestParam(required = false) String telefono,
                              @RequestParam(required = false) String citta,
                              HttpSession session) {

        /*commentata con prof - incogruenza tipo variabile fotoprofilo*/
    /*    if (result.hasErrors()) {
            System.out.println("result");
            System.out.println(result.toString());
            return "profilo_vet";
        }*/

        // Recupera il veterinario dalla sessione
        Veterinario veterinarioSession = (Veterinario) session.getAttribute("veterinario");
        veterinarioService.modificaDatiVeterinario(veterinarioSession.getId(), telefono, citta, fotoProfilo, session);

        return "redirect:/profilo_vet";
    }


}

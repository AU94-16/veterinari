package com.example.veterinari.controller;


import com.example.veterinari.model.Veterinario;
import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/profilo_vet")
public class ProfiloVetController {


    @Autowired
    private VeterinarioService veterinarioService;


    @GetMapping
    public Veterinario getdatiVeterinario(@RequestParam int id ) {

        return veterinarioService.datiVeterinario(id);
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

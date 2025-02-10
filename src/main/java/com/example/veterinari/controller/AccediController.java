package com.example.veterinari.controller;

import com.example.veterinari.service.VeterinarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/accedi
@Controller
@RequestMapping("/accedi")
public class AccediController {

    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public  String getPage(
            @RequestParam(required = false) String errore,
            Model model,
            HttpSession session) {
        if(session.getAttribute("veterinario") != null){
            return "redirect:/area_riservata";
        }
        model.addAttribute("errore", errore);

        return "accedi";
    }

    @PostMapping
    public String formManager(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session) {
        if(!veterinarioService.loginVeterinario(email, password, session))
            return "redirect:/accedi?errore";
        return "redirect:/area_riservata";
    }
}

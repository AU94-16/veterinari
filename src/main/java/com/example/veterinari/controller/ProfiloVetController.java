package com.example.veterinari.controller;

import com.example.veterinari.model.Animale;
import com.example.veterinari.service.AnimaleService;
import com.example.veterinari.service.ProprietarioService;
import com.example.veterinari.service.VeterinarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/profilo_vet")
public class ProfiloVetController {


    @Autowired
    private VeterinarioService veterinarioService;


    @GetMapping
    public String getPage(Model model) {


        return "profilo_vet";
    }





}

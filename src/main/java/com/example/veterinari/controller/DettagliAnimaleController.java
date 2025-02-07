package com.example.veterinari.controller;

import com.example.veterinari.service.AnimaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// localhost:8080/animale
@Controller
@RequestMapping ("/animale")
public class DettagliAnimaleController {

    @Autowired
    private AnimaleService animaleService;

    @GetMapping
    public String getPage(@RequestParam int id, Model model, HttpSession session){

    }
}

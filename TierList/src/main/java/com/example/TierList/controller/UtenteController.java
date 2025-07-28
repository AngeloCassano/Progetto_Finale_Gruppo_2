package com.example.TierList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TierList.repository.UtenteRepository;

@RestController
@RequestMapping("/user")
public class UtenteController
{
    @Autowired
    private UtenteRepository utenteRepository;

    @GetMapping("/info")
    public String infoUtente(Authentication auth) {
        return "Ciao " + auth.getName() + ", sei autenticato come USER o ADMIN.";
    }


}

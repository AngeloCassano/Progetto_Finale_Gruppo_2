package com.example.TierList.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/hello")
    public String salutoPubblico() {
        return "Benvenuto! Questa è una rotta pubblica.";
    }
}

package com.example.TierList.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
@GetMapping("/pannello")
public String pannelloAdmin(Authentication auth) {
return "Benvenuto " + auth.getName() + ", sei un ADMIN.";
}
}

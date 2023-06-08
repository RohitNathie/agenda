package com.example.demo.controller;

import com.example.demo.repository.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.UserCredentials;

@Controller

public class HomeController {
    @Autowired
    UserDataRepo repo;
    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String processLogin(@ModelAttribute UserCredentials userCredentials, Model model) {
        // Print username and password to console
        System.out.print(userCredentials.getUsername());
        System.out.print(userCredentials.getPassword());
        // Find user by username
        UserCredentials savedUser = repo.findByUsername(userCredentials.getUsername());
        // Check if savedUser is not null and password matches
        if (savedUser != null && savedUser.getPassword().equals(userCredentials.getPassword())) {
            // Authentication successful
            return "redirect:/succeslogin";
        } else {
            // Invalid username or password
            model.addAttribute("error", true);
            return "redirect:/";
        }
    }

    @GetMapping("/registration")
    public String register() {
        return "registration";
    }

    @PostMapping("/saveData")
    public String saveData(@ModelAttribute UserCredentials userCredentials) {
        // Save user to database
        repo.save(userCredentials);
        return "succes";
    }


}

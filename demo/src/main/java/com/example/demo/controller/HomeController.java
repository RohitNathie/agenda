package com.example.demo.controller;

import com.example.demo.repository.UserDataRepo;
import com.example.demo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.UserCredentials;

@Controller

public class HomeController {

    private final UsersService usersService;
    public HomeController(UsersService usersService) {
        this.usersService = usersService;
    }
    // field injection
    @Autowired
    UserDataRepo repo;



    // handler for login page
    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("loginRequest", new UserCredentials());
        return "login";
    }

    // Handler for the login form submission
    @PostMapping("/login")
    public String processLogin(@ModelAttribute UserCredentials userCredentials, Model model) {
        System.out.println("Login request: " + userCredentials);
        // Authenticate the user using the credentials provided
        UserCredentials authenticated = usersService.authenticate(userCredentials.getUsername(), userCredentials.getPassword());
        // Redirect to the success page or error page
        if(authenticated != null) {
            model.addAttribute("userLogin", authenticated.getUsername());
            return "succeslogin";
        }else{
            return "error_page";
        }
    }
    // Handler for the registration page
    @GetMapping("/registration")
    public String register(Model model) {
        model.addAttribute("registerRequest", new UserCredentials());
        return "registration";
    }

    // Handler to save user data from the registration form
    @PostMapping("/registration")
    public String saveData(@ModelAttribute UserCredentials userCredentials, Model model) {
        // Save the data to the database
        UserCredentials registeredUser = usersService.registerUser(userCredentials.getUsername(), userCredentials.getPassword());
        // Redirect to the login page or error page
        if (registeredUser != null) {
            // Registration successful, set attribute and return success view name
            model.addAttribute("username", registeredUser.getUsername());
            return "succesRegistration";
        } else {
            // Error occurred during registration, return error view name
            return "error_page_registration";
        }
    }
    // Handler for the success page after successful login
    @GetMapping("/success")
    public String success() {
        return "succesRegistration";
    }

    // Handler to redirect back to the login page after a delay
    @GetMapping("/redirect")
    public String redirectAfterDelay() {
        return "redirect:/login";
    }


}

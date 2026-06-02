package com.example.motogarage.controller;

import com.example.motogarage.repository.MaintenanceRepository;
import com.example.motogarage.repository.VehicleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebController {

    private final VehicleRepository vehicleRepository;
    private final MaintenanceRepository maintenanceRepository;

    public WebController(VehicleRepository vehicleRepository, MaintenanceRepository maintenanceRepository) {
        this.vehicleRepository = vehicleRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {

        model.addAttribute("vehicles", vehicleRepository.findAll());
        model.addAttribute("maintenances", maintenanceRepository.findAll());


        if (principal != null) {
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "Misafir");
        }

        return "index";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
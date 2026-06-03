package com.example.motogarage.controller;

import com.example.motogarage.entity.Maintenance;
import com.example.motogarage.entity.Vehicle;
import com.example.motogarage.repository.MaintenanceRepository;
import com.example.motogarage.repository.VehicleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
            var authorities = ((UsernamePasswordAuthenticationToken) principal).getAuthorities();
            boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            model.addAttribute("isAdmin", isAdmin);
        } else {
            model.addAttribute("username", "Misafir");
            model.addAttribute("isAdmin", false);
        }
        return "index";
    }


    @PostMapping("/save-vehicle")
    public String saveVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "redirect:/";
    }

    @GetMapping("/delete-vehicle/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit-vehicle/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", vehicleRepository.findById(id).orElseThrow());
        return "edit-vehicle";
    }

    @PostMapping("/update-vehicle")
    public String updateVehicle(@ModelAttribute Vehicle vehicle) {
        vehicleRepository.save(vehicle);
        return "redirect:/";
    }


    @PostMapping("/save-maintenance")
    public String saveMaintenance(@ModelAttribute Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
        return "redirect:/";
    }

    @GetMapping("/delete-maintenance/{id}")
    public String deleteMaintenance(@PathVariable Long id) {
        maintenanceRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/edit-maintenance/{id}")
    public String editMaintenanceForm(@PathVariable Long id, Model model) {
        model.addAttribute("maintenance", maintenanceRepository.findById(id).orElseThrow());
        return "edit-maintenance";
    }

    @PostMapping("/update-maintenance")
    public String updateMaintenance(@ModelAttribute Maintenance maintenance) {
        maintenanceRepository.save(maintenance);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
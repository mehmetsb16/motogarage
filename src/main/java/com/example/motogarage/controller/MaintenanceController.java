package com.example.motogarage.controller;

import jakarta.validation.Valid;
import com.example.motogarage.dto.MaintenanceRequest;
import com.example.motogarage.entity.Maintenance;
import com.example.motogarage.service.MaintenanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenances")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public List<Maintenance> getAllMaintenances() {
        return maintenanceService.getAllMaintenances();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> getMaintenanceById(@PathVariable Long id) {
        return maintenanceService.getMaintenanceById(id)
                .map(maintenance -> ResponseEntity.ok(maintenance))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Maintenance> createMaintenance(@Valid @RequestBody MaintenanceRequest request) {
        Maintenance createdMaintenance = maintenanceService.createMaintenance(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaintenance);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceRequest request
    ) {
        Maintenance updatedMaintenance = maintenanceService.updateMaintenance(id, request);

        if (updatedMaintenance != null) {
            return ResponseEntity.ok(updatedMaintenance);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaintenance(@PathVariable Long id) {
        boolean deleted = maintenanceService.deleteMaintenance(id);

        if (deleted) {
            return ResponseEntity.ok("Bakım kaydı silindi.");
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/completed/{completed}")
    public List<Maintenance> getMaintenancesByCompletedStatus(@PathVariable boolean completed) {
        return maintenanceService.getMaintenancesByCompletedStatus(completed);
    }

    @GetMapping("/search")
    public List<Maintenance> searchMaintenancesByTitle(@RequestParam String title) {
        return maintenanceService.searchMaintenancesByTitle(title);
    }

    @GetMapping("/filter")
    public List<Maintenance> filterMaintenances(
            @RequestParam String title,
            @RequestParam boolean completed
    ) {
        return maintenanceService.filterMaintenancesByTitleAndCompleted(title, completed);
    }

    @GetMapping("/count")
    public long countMaintenancesByCompletedStatus(@RequestParam boolean completed) {
        return maintenanceService.countMaintenancesByCompletedStatus(completed);
    }

    @GetMapping("/exists")
    public boolean existsMaintenanceByTitle(@RequestParam String title) {
        return maintenanceService.existsMaintenanceByTitle(title);
    }

    @GetMapping("/latest")
    public List<Maintenance> getLatestFiveMaintenances() {
        return maintenanceService.getLatestFiveMaintenances();
    }

    @PostMapping("/rollback-test")
    public ResponseEntity<Maintenance> createMaintenanceWithRollbackTest(@Valid @RequestBody MaintenanceRequest request) {
        Maintenance createdMaintenance = maintenanceService.createMaintenanceWithRollbackTest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaintenance);
    }
}
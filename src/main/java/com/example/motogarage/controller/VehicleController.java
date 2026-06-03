package com.example.motogarage.controller;

import com.example.motogarage.entity.Vehicle;
import com.example.motogarage.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }


    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleRepository.findById(id)
                .map(existingVehicle -> {
                    existingVehicle.setBrand(vehicle.getBrand());
                    existingVehicle.setModel(vehicle.getModel());
                    existingVehicle.setPlate(vehicle.getPlate());
                    return ResponseEntity.ok(vehicleRepository.save(existingVehicle));
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok("Araç başarıyla silindi.");
        }
        return ResponseEntity.notFound().build();
    }
}
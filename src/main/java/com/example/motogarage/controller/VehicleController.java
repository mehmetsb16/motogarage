package com.example.motogarage.controller;

import com.example.motogarage.entity.Vehicle;
import com.example.motogarage.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles") // Tüm isteklerin yolu buradan başlar
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // POSTMAN ile JSON eklemek için
    @PostMapping
    public Vehicle createVehicle(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // POSTMAN ile listelemek için
    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // POSTMAN ile güncellemek için
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

    // POSTMAN ile silmek için
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Long id) {
        if (vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok("Araç başarıyla silindi.");
        }
        return ResponseEntity.notFound().build();
    }
}
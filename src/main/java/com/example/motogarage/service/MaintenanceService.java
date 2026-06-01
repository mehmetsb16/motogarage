package com.example.motogarage.service;

import com.example.motogarage.dto.MaintenanceRequest;
import com.example.motogarage.entity.Maintenance;
import com.example.motogarage.entity.Vehicle;
import com.example.motogarage.repository.MaintenanceRepository;
import com.example.motogarage.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository, VehicleRepository vehicleRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    public Optional<Maintenance> getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id);
    }

    public List<Maintenance> getMaintenancesByVehicleId(Long vehicleId) {
        return maintenanceRepository.findByVehicleId(vehicleId);
    }

    @Transactional
    public Maintenance createMaintenance(MaintenanceRequest request) {
        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Araç bulunamadı: " + request.getVehicleId()));

        Maintenance maintenance = new Maintenance();
        maintenance.setTitle(request.getTitle());
        maintenance.setDescription(request.getDescription());
        maintenance.setCompleted(request.getCompleted() != null ? request.getCompleted() : false);
        maintenance.setVehicle(vehicle);

        return maintenanceRepository.save(maintenance);
    }

    @Transactional
    public Maintenance updateMaintenance(Long id, MaintenanceRequest request) {
        Optional<Maintenance> optionalMaintenance = maintenanceRepository.findById(id);

        if (optionalMaintenance.isPresent()) {
            Maintenance existingMaintenance = optionalMaintenance.get();
            Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                    .orElseThrow(() -> new RuntimeException("Araç bulunamadı!"));

            existingMaintenance.setTitle(request.getTitle());
            existingMaintenance.setDescription(request.getDescription());
            existingMaintenance.setCompleted(request.getCompleted() != null ? request.getCompleted() : existingMaintenance.isCompleted());
            existingMaintenance.setVehicle(vehicle);

            return maintenanceRepository.save(existingMaintenance);
        }
        return null;
    }

    @Transactional
    public boolean deleteMaintenance(Long id) {
        if (maintenanceRepository.existsById(id)) {
            maintenanceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Maintenance> getMaintenancesByCompletedStatus(boolean completed) {
        return maintenanceRepository.findByCompleted(completed);
    }

    public List<Maintenance> searchMaintenancesByTitle(String title) {
        return maintenanceRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Maintenance> filterMaintenancesByTitleAndCompleted(String title, boolean completed) {
        return maintenanceRepository.findByTitleContainingIgnoreCaseAndCompleted(title, completed);
    }

    public long countMaintenancesByCompletedStatus(boolean completed) {
        return maintenanceRepository.countByCompleted(completed);
    }

    public boolean existsMaintenanceByTitle(String title) {
        return maintenanceRepository.existsByTitleIgnoreCase(title);
    }

    public List<Maintenance> getLatestFiveMaintenances() {
        return maintenanceRepository.findTop5ByOrderByIdDesc();
    }

    @Transactional
    public Maintenance createMaintenanceWithRollbackTest(MaintenanceRequest request) {
        return createMaintenance(request);
    }
}
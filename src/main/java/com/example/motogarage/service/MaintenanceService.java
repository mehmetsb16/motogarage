package com.example.motogarage.service;

import com.example.motogarage.dto.MaintenanceRequest;
import com.example.motogarage.entity.Maintenance;
import com.example.motogarage.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    public Optional<Maintenance> getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id);
    }

    @Transactional
    public Maintenance createMaintenance(MaintenanceRequest request) {
        Maintenance maintenance = new Maintenance();

        maintenance.setTitle(request.getTitle());
        maintenance.setDescription(request.getDescription());

        if (request.getCompleted() != null) {
            maintenance.setCompleted(request.getCompleted());
        } else {
            maintenance.setCompleted(false);
        }

        return maintenanceRepository.save(maintenance);
    }

    @Transactional
    public Maintenance updateMaintenance(Long id, MaintenanceRequest request) {
        Optional<Maintenance> optionalMaintenance = maintenanceRepository.findById(id);

        if (optionalMaintenance.isPresent()) {
            Maintenance existingMaintenance = optionalMaintenance.get();

            existingMaintenance.setTitle(request.getTitle());
            existingMaintenance.setDescription(request.getDescription());

            if (request.getCompleted() != null) {
                existingMaintenance.setCompleted(request.getCompleted());
            }

            return maintenanceRepository.save(existingMaintenance);
        }

        return null;
    }

    @Transactional
    public boolean deleteMaintenance(Long id) {
        Optional<Maintenance> optionalMaintenance = maintenanceRepository.findById(id);

        if (optionalMaintenance.isPresent()) {
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
        Maintenance maintenance = new Maintenance();

        maintenance.setTitle(request.getTitle());
        maintenance.setDescription(request.getDescription());

        if (request.getCompleted() != null) {
            maintenance.setCompleted(request.getCompleted());
        } else {
            maintenance.setCompleted(false);
        }

        Maintenance savedMaintenance = maintenanceRepository.save(maintenance);

        if (true) {
            throw new RuntimeException("Rollback testi için bilinçli hata oluşturuldu.");
        }

        return savedMaintenance;
    }
}
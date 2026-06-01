package com.example.motogarage.repository;

import com.example.motogarage.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByCompleted(boolean completed);

    List<Maintenance> findByTitleContainingIgnoreCase(String title);

    List<Maintenance> findByTitleContainingIgnoreCaseAndCompleted(String title, boolean completed);

    List<Maintenance> findByVehicleId(Long vehicleId);

    long countByCompleted(boolean completed);

    boolean existsByTitleIgnoreCase(String title);

    List<Maintenance> findTop5ByOrderByIdDesc();
}
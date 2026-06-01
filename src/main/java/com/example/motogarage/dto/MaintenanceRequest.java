package com.example.motogarage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MaintenanceRequest {

    @NotBlank(message = "Bakım başlığı boş bırakılamaz.")
    @Size(min = 3, max = 100, message = "Bakım başlığı 3 ile 100 karakter arasında olmalıdır.")
    private String title;

    @Size(max = 500, message = "Açıklama en fazla 500 karakter olabilir.")
    private String description;

    private Boolean completed;

    @NotNull(message = "Araç ID'si boş bırakılamaz.")
    private Long vehicleId;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(String title, String description, Boolean completed, Long vehicleId) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.vehicleId = vehicleId;
    }

    // Getter ve Setter metotları
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
package com.example.motogarage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private boolean completed;

    // Araca bağladığımız kısım:
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
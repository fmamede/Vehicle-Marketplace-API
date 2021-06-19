package com.github.fmd00.VehicleMarketplaceAPI.entity;

import com.github.fmd00.VehicleMarketplaceAPI.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrivetrainType drivetrainType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmissionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleColor vehicleColor;

    @ElementCollection
    private List<VehicleFeatures> vehicleFeaturesList;

    @ManyToOne
    @JoinColumn(name = "\"seller\"", referencedColumnName = "\"id\"")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seller seller;
}

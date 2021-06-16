package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

//    @OneToMany(mappedBy = "id", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
//    private List<Vehicle> vehicles/* = new ArrayList<>()*/;

    @OneToMany(mappedBy = "id", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<Vehicle> vehicles/* = new ArrayList<>()*/;
}

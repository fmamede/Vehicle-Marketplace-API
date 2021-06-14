package com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;

import com.github.fmd00.VehicleMarketplaceAPI.entity.Seller.Seller;
import com.github.fmd00.VehicleMarketplaceAPI.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String make;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String model;

    @Min(1900)
    @Max(2200)
    private Integer year;

    @Min(1)
    @Max(10000000)
    private Integer price;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private DrivetrainType drivetrainType;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @Enumerated(EnumType.STRING)
    private VehicleColor vehicleColor;

    @ElementCollection
    private List<VehicleFeatures> vehicleFeaturesList;
    
    private Seller seller;
}

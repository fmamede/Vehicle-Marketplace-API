package com.github.fmd00.VehicleMarketplaceAPI.builder;

import com.github.fmd00.VehicleMarketplaceAPI.dto.SellerDTO;
import com.github.fmd00.VehicleMarketplaceAPI.dto.VehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.enums.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class VehicleDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String make = "Subaru";

    @Builder.Default
    private String model = "Impreza Premium";

    @Builder.Default
    private Integer year = 2021;

    @Builder.Default
    private Integer price = 24400;

    @Builder.Default
    private VehicleType vehicleType = VehicleType.CAR;

    @Builder.Default
    private DrivetrainType drivetrainType = DrivetrainType.ALL_WHEEL_DRIVE;

    @Builder.Default
    private TransmissionType transmissionType = TransmissionType.AUTOMATIC;

    @Builder.Default
    private VehicleColor vehicleColor = VehicleColor.BLACK;

    @Builder.Default
    private List<VehicleFeatures> vehicleFeaturesList = new ArrayList<VehicleFeatures>();

    @Builder.Default
    private SellerDTO sellerDTO = new SellerDTO(1L, "Marcus Houston", "1111111111");

    public VehicleDTO toVehicleDTO() {
        return new VehicleDTO(id, make, model, year, price, vehicleType, drivetrainType, transmissionType, vehicleColor, vehicleFeaturesList, sellerDTO);
    }
}

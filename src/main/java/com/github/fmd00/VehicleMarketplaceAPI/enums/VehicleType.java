package com.github.fmd00.VehicleMarketplaceAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleType {
    TRUCK("Truck"),
    CAR("Car"),
    MOTORCYCLE("Motorcycle");

    private final String description;
}

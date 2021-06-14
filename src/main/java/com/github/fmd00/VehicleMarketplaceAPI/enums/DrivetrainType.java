package com.github.fmd00.VehicleMarketplaceAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DrivetrainType {
    ALL_WHEEL_DRIVE("All-Wheel drive"),
    FOUR_WHEEL_DRIVE("4x4/4-wheel drive"),
    REAR_WHEEL_DRIVE("Rear-wheel drive"),
    FORWARD_WHEEL_DRIVE("Forward-wheel drive");

    private final String description;
}

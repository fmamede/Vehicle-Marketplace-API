package com.github.fmd00.VehicleMarketplaceAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransmissionType {
    AUTOMATIC("Automatic"),
    MANUAL("Manual");

    private final String description;
}

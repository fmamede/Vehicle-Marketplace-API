package com.github.fmd00.VehicleTradeAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransmissionType {
    MANUAL("Manual"),
    AUTOMATIC("Automatic");

    private final String transmissionType;
}

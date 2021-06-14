package com.github.fmd00.VehicleMarketplaceAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleColor {
    BLACK("Black"),
    WHITE("White"),
    SILVER("Silver"),
    RED("Red"),
    BLUE("Blue"),
    YELLOW("Yellow"),
    GREEN("Green");

    private final String description;
}

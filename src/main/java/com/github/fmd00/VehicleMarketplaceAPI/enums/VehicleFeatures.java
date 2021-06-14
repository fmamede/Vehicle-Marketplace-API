package com.github.fmd00.VehicleMarketplaceAPI.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleFeatures {

    ADAPTIVE_CRUISE_CONTROL("Adaptive Cruise Control"),
    ALLOY_WHEELS("Alloy Wheels"),
    APPLE_CARPLAY_ANDROID_AUTO("Apple CarPlay/Android Auto"),
    AUTOMATIC_EMERGENCY_BRAKING("Automatic Emergency Braking"),
    BACKUP_CAMERA("Backup Camera"),
    BLIND_SPOT_MONITOR("Blind Spot Monitor"),
    BLUETOOTH("Bluetooth"),
    BRAKE_ASSIST("Brake Assist"),
    COOLED_SEATS("Cooled Seats"),
    HEATED_SEATS("Heated Seats"),
    HEATED_STEERING_WHEEL("Heated Steering Wheel"),
    HOMELINK("HomeLink"),
    KEYLESS_START("Keyless Start"),
    LANE_DEPARTURE_WARNING("Lane Departure Warning"),
    LEATHER_SEATS("Leather Seats"),
    LED_HEADLIGHTS("LED Headlights"),
    MEMORY_SEAT("Memory Seat"),
    NAVIGATION_SYSTEM("Navigation System"),
    PREMIUM_SOUND_SYSTEM("Premium Sound System"),
    REAR_CROSS_TRAFFIC_ALERT("Rear Cross Traffic Alert"),
    REAR_SEAT_ENTERTAINMENT("Rear Seat Entertainment"),
    REMOTE_START("Remote Start"),
    STABILITY_CONTROL("Stability Control"),
    SUNROOF_MOONROOF("Sunroof/Moonroof"),
    THIRD_ROW_SEATING("Third Row Seating"),
    TOW_HITCH("Tow Hitch"),
    USB_PORT("USB Port");

    private final String description;
}

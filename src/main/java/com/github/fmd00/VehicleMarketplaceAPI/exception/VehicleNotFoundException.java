package com.github.fmd00.VehicleMarketplaceAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VehicleNotFoundException extends Exception {
    public VehicleNotFoundException(Long id) {
        super("Vehicle not found with id " + id);
    }
}

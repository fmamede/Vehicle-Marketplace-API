package com.github.fmd00.VehicleTradeAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SellerNotFoundException extends Exception {

    public SellerNotFoundException(Long id) {
        super("Seller not found with id " + id);
    }
}

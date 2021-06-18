package com.github.fmd00.VehicleMarketplaceAPI.controller;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.dto.PartialVehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.dto.VehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.exception.VehicleNotFoundException;
import com.github.fmd00.VehicleMarketplaceAPI.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/marketplace/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public VehicleDTO createVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        return vehicleService.createVehicle(vehicleDTO);
    }

    @GetMapping
    public List<VehicleDTO> listAll() {
        return vehicleService.listAll();
    }

    @GetMapping("/{id}")
    public VehicleDTO findById(@PathVariable Long id) throws VehicleNotFoundException {
        return vehicleService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws VehicleNotFoundException {
        vehicleService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        return vehicleService.updateById(id, vehicleDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO partiallyUpdateVehicleById(@PathVariable Long id, @RequestBody @Valid PartialVehicleDTO vehicleDTO) throws VehicleNotFoundException {
        return vehicleService.partiallyUpdateVehicleById(id, vehicleDTO);
    }
}

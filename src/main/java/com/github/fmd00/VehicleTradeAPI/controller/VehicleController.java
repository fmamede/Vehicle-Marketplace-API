package com.github.fmd00.VehicleTradeAPI.controller;

import com.github.fmd00.VehicleTradeAPI.dto.request.VehicleDTO;
import com.github.fmd00.VehicleTradeAPI.dto.response.MessageResponseDTO;
import com.github.fmd00.VehicleTradeAPI.exception.VehicleNotFoundException;
import com.github.fmd00.VehicleTradeAPI.service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/vehicles")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleController {
    private VehicleService vehicleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
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

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        return vehicleService.updateById(id, vehicleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws VehicleNotFoundException {
        vehicleService.delete(id);
    }
}


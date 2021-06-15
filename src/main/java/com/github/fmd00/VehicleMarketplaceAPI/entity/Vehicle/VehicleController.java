package com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.exception.VehicleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteById(@PathVariable Long id) throws VehicleNotFoundException {
        vehicleService.delete(id);
    }
}

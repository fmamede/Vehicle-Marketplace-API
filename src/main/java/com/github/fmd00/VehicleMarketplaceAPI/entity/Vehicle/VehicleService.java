package com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.exception.VehicleNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private VehicleRepository vehicleRepository;

    private final VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public MessageResponseDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicleToSave = vehicleMapper.toModel(vehicleDTO);

        Vehicle savedVehicle = vehicleRepository.save(vehicleToSave);
        return MessageResponseDTO.builder()
                .message("Created vehicle with id " + savedVehicle.getId()).build();
    }

    public List<VehicleDTO> listAll() {
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        return allVehicles.stream().map(vehicleMapper::toDTO).collect(Collectors.toList());
    }

    public VehicleDTO findById(Long id) throws VehicleNotFoundException {
        Vehicle vehicle = verifyIfVehicleExists(id);
        return vehicleMapper.toDTO(vehicle);
    }

    public void delete(Long id) throws VehicleNotFoundException {
        verifyIfVehicleExists(id);
        vehicleRepository.deleteById(id);
    }

    private Vehicle verifyIfVehicleExists(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }
}

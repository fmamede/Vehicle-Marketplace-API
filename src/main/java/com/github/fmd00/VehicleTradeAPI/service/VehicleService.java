package com.github.fmd00.VehicleTradeAPI.service;

import com.github.fmd00.VehicleTradeAPI.dto.request.VehicleDTO;
import com.github.fmd00.VehicleTradeAPI.dto.response.MessageResponseDTO;
import com.github.fmd00.VehicleTradeAPI.entity.Vehicle;
import com.github.fmd00.VehicleTradeAPI.exception.VehicleNotFoundException;
import com.github.fmd00.VehicleTradeAPI.mapper.VehicleMapper;
import com.github.fmd00.VehicleTradeAPI.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VehicleService {
    private VehicleRepository vehicleRepository;

    private final VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    public MessageResponseDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicleToSave = vehicleMapper.toModel(vehicleDTO);

        Vehicle createdVehicle = vehicleRepository.save(vehicleToSave);
        return createMessageResponse(createdVehicle, "Created vehicle with id ");
    }

    public List<VehicleDTO> listAll() {
        List<Vehicle> allPeople = vehicleRepository.findAll();
        return allPeople.stream().map(vehicleMapper::toDTO).collect(Collectors.toList());
    }

    public VehicleDTO findById(Long id) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
        return vehicleMapper.toDTO(vehicle);
    }

    public void delete(Long id) throws VehicleNotFoundException {
        verifyIfExists(id);

        vehicleRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        verifyIfExists(id);

        Vehicle vehicleToUpdate = vehicleMapper.toModel(vehicleDTO);

        Vehicle updatedVehicle = vehicleRepository.save(vehicleToUpdate);
        return createMessageResponse(updatedVehicle, "Updated vehicle with id ");
    }

    private Vehicle verifyIfExists(Long id) throws VehicleNotFoundException {
        return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Vehicle createdVehicle, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + createdVehicle.getId())
                .build();
    }
}

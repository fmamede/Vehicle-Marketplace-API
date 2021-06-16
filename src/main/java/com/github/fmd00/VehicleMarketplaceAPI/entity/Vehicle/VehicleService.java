package com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Seller.Seller;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Seller.SellerMapper;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle.PartialVehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle.Vehicle;
import com.github.fmd00.VehicleMarketplaceAPI.enums.*;
import com.github.fmd00.VehicleMarketplaceAPI.exception.VehicleNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
        return createMessageResponse(savedVehicle.getId(), "Created vehicle with id ");
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

    public MessageResponseDTO updateById(Long id, VehicleDTO vehicleDTO) throws VehicleNotFoundException {
        verifyIfVehicleExists(id);
        Vehicle vehicleToUpdate = vehicleMapper.toModel(vehicleDTO);

        Vehicle savedVehicle = vehicleRepository.save(vehicleToUpdate);
        return createMessageResponse(savedVehicle.getId(), "Updated vehicle with id ");
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + id).build();
    }

    public MessageResponseDTO partiallyUpdateVehicleById(Long id, PartialVehicleDTO partialVehicleDTO) throws VehicleNotFoundException {
        Vehicle vehicle = verifyIfVehicleExists(id);

        Vehicle vehicleToUpdate = partiallyUpdateVehicle(partialVehicleDTO, vehicle);

        Vehicle savedVehicle = vehicleRepository.save(vehicleToUpdate);
        return createMessageResponse(savedVehicle.getId(), "Updated vehicle with id ");
    }

    private Vehicle partiallyUpdateVehicle(PartialVehicleDTO vehicleDTO, Vehicle vehicle) {
        String tempMake = vehicleDTO.getMake() != null ? vehicleDTO.getMake() : vehicle.getMake();
        String tempModel = vehicleDTO.getModel() != null ? vehicleDTO.getModel() : vehicle.getModel();
        @Min(1900) @Max(2200) Integer tempYear = vehicleDTO.getYear() != null ? vehicleDTO.getYear() : vehicle.getYear();
        @Min(1) @Max(10000000) Integer tempPrice = vehicleDTO.getPrice() != null ? vehicleDTO.getPrice() : vehicle.getPrice();
        VehicleType tempVehicleType = vehicleDTO.getVehicleType() != null ? vehicleDTO.getVehicleType() : vehicle.getVehicleType();
        DrivetrainType tempDrivetrainType = vehicleDTO.getDrivetrainType() != null ? vehicleDTO.getDrivetrainType() : vehicle.getDrivetrainType();
        TransmissionType tempTransmissionType = vehicleDTO.getTransmissionType() != null ? vehicleDTO.getTransmissionType() : vehicle.getTransmissionType();
        VehicleColor tempVehicleColor = vehicleDTO.getVehicleColor() != null ? vehicleDTO.getVehicleColor() : vehicle.getVehicleColor();
        List<VehicleFeatures> tempVehicleFeaturesList = vehicleDTO.getVehicleFeaturesList() != null ? vehicleDTO.getVehicleFeaturesList() : vehicle.getVehicleFeaturesList();
        Seller tempSeller = vehicleDTO.getSeller() != null ? SellerMapper.INSTANCE.toModel(vehicleDTO.getSeller()) : vehicle.getSeller();

        return Vehicle.builder()
                .id(vehicle.getId())
                .make(tempMake)
                .model(tempModel)
                .year(tempYear)
                .price(tempPrice)
                .vehicleType(tempVehicleType)
                .drivetrainType(tempDrivetrainType)
                .transmissionType(tempTransmissionType)
                .vehicleColor(tempVehicleColor)
                .vehicleFeaturesList(tempVehicleFeaturesList)
                .seller(tempSeller)
                .build();
    }
}

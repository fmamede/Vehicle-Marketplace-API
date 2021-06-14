package com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle toModel(VehicleDTO vehicleDTO);

    VehicleDTO toDTO(Vehicle vehicle);
}

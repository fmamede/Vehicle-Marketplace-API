package com.github.fmd00.VehicleMarketplaceAPI.mapper;

import com.github.fmd00.VehicleMarketplaceAPI.dto.VehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    Vehicle toModel(VehicleDTO vehicleDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateVehicle(@MappingTarget Vehicle vehicle, VehicleDTO vehicleDTO);

    @Mapping(target = "seller", source = "seller")
    VehicleDTO toDTO(Vehicle vehicle);
}

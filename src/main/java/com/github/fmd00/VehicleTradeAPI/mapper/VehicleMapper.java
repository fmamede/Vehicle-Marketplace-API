package com.github.fmd00.VehicleTradeAPI.mapper;

import com.github.fmd00.VehicleTradeAPI.dto.request.VehicleDTO;
import com.github.fmd00.VehicleTradeAPI.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehicleMapper {
    VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    @Mapping(target = "purchaseDate", source = "purchaseDate", dateFormat = "dd-MM-yyyy")
    Vehicle toModel(VehicleDTO vehicleDTO);

    VehicleDTO toDTO(Vehicle vehicle);
}

package com.github.fmd00.VehicleTradeAPI.mapper;

import com.github.fmd00.VehicleTradeAPI.dto.request.SellerDTO;
import com.github.fmd00.VehicleTradeAPI.entity.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {
    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    Seller toModel(SellerDTO sellerDTO);

    SellerDTO toDTO(Seller seller);
}

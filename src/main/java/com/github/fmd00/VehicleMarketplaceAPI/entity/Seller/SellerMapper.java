package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    Seller toModel(SellerDTO sellerDTO);

    SellerDTO toDTO(Seller seller);
}

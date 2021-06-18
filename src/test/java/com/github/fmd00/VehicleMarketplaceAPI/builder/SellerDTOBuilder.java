package com.github.fmd00.VehicleMarketplaceAPI.builder;

import com.github.fmd00.VehicleMarketplaceAPI.dto.SellerDTO;
import lombok.Builder;

@Builder
public class SellerDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Marcus Houston";

    @Builder.Default
    private String phoneNumber = "1111111111";

    public SellerDTO toSellerDTO() {
        return new SellerDTO(id, name, phoneNumber);
    }
}

package com.github.fmd00.VehicleMarketplaceAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartialSellerDTO {

    private Long id;

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 10, max = 10)
    private String phoneNumber;

}
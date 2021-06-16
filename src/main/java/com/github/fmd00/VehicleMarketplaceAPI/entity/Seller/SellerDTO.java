package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {

    private Long id;

    @NotEmpty
    @Size(min = 1, max = 50)
    private String name;

    @NotEmpty
    @Size(min = 10, max = 10)
    private String phoneNumber;

}

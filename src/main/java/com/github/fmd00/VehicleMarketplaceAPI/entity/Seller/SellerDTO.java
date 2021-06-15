package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {

    private Long id;

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 10, max = 10)
    private String phoneNumber;

//    private List<Vehicle> vehicles;
}

package com.github.fmd00.VehicleTradeAPI.dto.request;

import com.github.fmd00.VehicleTradeAPI.entity.Seller;
import com.github.fmd00.VehicleTradeAPI.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {

    private Long id;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String make;

    @NotEmpty
    @Size(min = 1, max = 20)
    private String model;

    @NotEmpty
    private Integer year;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @NotEmpty
    private Long price;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private DrivetrainType drivetrainType;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private VehicleColor vehicleColor;

    @Column
    private List<VehicleFeatures> features;

    @Column
    private String purchaseDate;

    @Valid
    private Seller seller;
}

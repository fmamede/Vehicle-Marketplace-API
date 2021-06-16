package com.github.fmd00.VehicleMarketplaceAPI.repository;

import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}

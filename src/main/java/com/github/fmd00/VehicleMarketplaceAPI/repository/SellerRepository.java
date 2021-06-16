package com.github.fmd00.VehicleMarketplaceAPI.repository;

import com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}

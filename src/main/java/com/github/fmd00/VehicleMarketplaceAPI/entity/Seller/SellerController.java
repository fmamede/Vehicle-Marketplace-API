package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private SellerRepository sellerRepository;

    @Autowired
    public SellerController(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @PostMapping
    public MessageResponseDTO createSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerRepository.save(seller);
        return MessageResponseDTO.builder()
                .message("Created seller with id " + savedSeller.getId()).build();
    }
}

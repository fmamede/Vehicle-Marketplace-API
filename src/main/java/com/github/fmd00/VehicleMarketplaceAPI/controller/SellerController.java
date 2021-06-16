package com.github.fmd00.VehicleMarketplaceAPI.controller;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.dto.PartialSellerDTO;
import com.github.fmd00.VehicleMarketplaceAPI.dto.SellerDTO;
import com.github.fmd00.VehicleMarketplaceAPI.service.SellerService;
import com.github.fmd00.VehicleMarketplaceAPI.exception.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/marketplace/sellers")
public class SellerController {

    private SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createSeller(@RequestBody @Valid SellerDTO sellerDTO) {
        return sellerService.createSeller(sellerDTO);
    }

    @GetMapping
    public List<SellerDTO> listAll() {
        return sellerService.listAll();
    }

    @GetMapping("/{id}")
    public SellerDTO findById(@PathVariable Long id) throws SellerNotFoundException {
        return sellerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void deleteById(@PathVariable Long id) throws SellerNotFoundException {
        sellerService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid SellerDTO sellerDTO) throws SellerNotFoundException {
        return sellerService.updateById(id, sellerDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageResponseDTO partiallyUpdateSellerById(@PathVariable Long id, @RequestBody @Valid PartialSellerDTO partialSellerDTO) throws SellerNotFoundException {
        return sellerService.partiallyUpdateSellerById(id, partialSellerDTO);
    }
}

package com.github.fmd00.VehicleTradeAPI.controller;

import com.github.fmd00.VehicleTradeAPI.dto.request.SellerDTO;
import com.github.fmd00.VehicleTradeAPI.dto.response.MessageResponseDTO;
import com.github.fmd00.VehicleTradeAPI.exception.SellerNotFoundException;
import com.github.fmd00.VehicleTradeAPI.service.SellerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/sellers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SellerController {
    private SellerService sellerService;

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

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid SellerDTO sellerDTO) throws SellerNotFoundException {
        return sellerService.updateById(id, sellerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws SellerNotFoundException {
        sellerService.delete(id);
    }
}
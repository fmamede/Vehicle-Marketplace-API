package com.github.fmd00.VehicleTradeAPI.service;

import com.github.fmd00.VehicleTradeAPI.dto.request.SellerDTO;
import com.github.fmd00.VehicleTradeAPI.dto.response.MessageResponseDTO;
import com.github.fmd00.VehicleTradeAPI.entity.Seller;
import com.github.fmd00.VehicleTradeAPI.exception.SellerNotFoundException;
import com.github.fmd00.VehicleTradeAPI.mapper.SellerMapper;
import com.github.fmd00.VehicleTradeAPI.repository.SellerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SellerService {
    private SellerRepository sellerRepository;

    private final SellerMapper sellerMapper = SellerMapper.INSTANCE;

    public MessageResponseDTO createSeller(SellerDTO sellerDTO) {
        Seller sellerToSave = sellerMapper.toModel(sellerDTO);

        Seller createdSeller = sellerRepository.save(sellerToSave);
        return createMessageResponse(createdSeller, "Created seller with id ");
    }

    public List<SellerDTO> listAll() {
        List<Seller> allSellers = sellerRepository.findAll();
        return allSellers.stream().map(sellerMapper::toDTO).collect(Collectors.toList());
    }

    public SellerDTO findById(Long id) throws SellerNotFoundException {
        Seller seller = sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
        return sellerMapper.toDTO(seller);
    }

    public void delete(Long id) throws SellerNotFoundException {
        verifyIfExists(id);

        sellerRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, SellerDTO sellerDTO) throws SellerNotFoundException {
        verifyIfExists(id);

        Seller sellerToUpdate = sellerMapper.toModel(sellerDTO);

        Seller updatedSeller = sellerRepository.save(sellerToUpdate);
        return createMessageResponse(updatedSeller, "Updated seller with id ");
    }

    private Seller verifyIfExists(Long id) throws SellerNotFoundException {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Seller createdSeller, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + createdSeller.getId())
                .build();
    }
}
package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.exception.SellerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private SellerRepository sellerRepository;

    private final SellerMapper sellerMapper = SellerMapper.INSTANCE;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public MessageResponseDTO createSeller(SellerDTO sellerDTO) {
        Seller sellerToSave = sellerMapper.toModel(sellerDTO);

        Seller savedSeller = sellerRepository.save(sellerToSave);
        return MessageResponseDTO.builder()
                .message("Created seller with id " + savedSeller.getId()).build();
    }

    public List<SellerDTO> listAll() {
        List<Seller> allSellers = sellerRepository.findAll();
        return allSellers.stream().map(sellerMapper::toDTO).collect(Collectors.toList());
    }

    public SellerDTO findById(Long id) throws SellerNotFoundException {
        Seller seller = verifyIfSellerExists(id);
        return sellerMapper.toDTO(seller);
    }

    private Seller verifyIfSellerExists(Long id) throws SellerNotFoundException {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
    }

    public void delete(Long id) throws SellerNotFoundException {
        verifyIfSellerExists(id);
        sellerRepository.deleteById(id);
    }
}

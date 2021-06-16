package com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;

import com.github.fmd00.VehicleMarketplaceAPI.dto.MessageResponseDTO;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle.Vehicle;
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
        return createMessageResponse(savedSeller.getId(), "Created seller with id ");
    }

    public List<SellerDTO> listAll() {
        List<Seller> allSellers = sellerRepository.findAll();
        return allSellers.stream().map(sellerMapper::toDTO).collect(Collectors.toList());
    }

    public SellerDTO findById(Long id) throws SellerNotFoundException {
        Seller seller = verifyIfSellerExists(id);
        return sellerMapper.toDTO(seller);
    }

    public void delete(Long id) throws SellerNotFoundException {
        verifyIfSellerExists(id);
        sellerRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, SellerDTO sellerDTO) throws SellerNotFoundException {
        verifyIfSellerExists(id);

        Seller sellerToUpdate = sellerMapper.toModel(sellerDTO);

        Seller savedSeller = sellerRepository.save(sellerToUpdate);
        return createMessageResponse(savedSeller.getId(), "Updated seller with id ");
    }

    public MessageResponseDTO partiallyUpdateSellerById(Long id, PartialSellerDTO partialSellerDTO) throws SellerNotFoundException {
        Seller seller = verifyIfSellerExists(id);

        Seller sellerToUpdate = partiallyUpdateSeller(partialSellerDTO, seller);

        Seller savedSeller = sellerRepository.save(sellerToUpdate);
        return createMessageResponse(savedSeller.getId(), "Updated seller with id ");
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + id).build();
    }

    private Seller verifyIfSellerExists(Long id) throws SellerNotFoundException {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFoundException(id));
    }

    private Seller partiallyUpdateSeller(PartialSellerDTO sellerDTO, Seller seller) {
        String tempName = sellerDTO.getName() != null ? sellerDTO.getName() : seller.getName();
        String tempPhoneNumber = sellerDTO.getPhoneNumber() != null ? sellerDTO.getPhoneNumber() : seller.getPhoneNumber();
        List<Vehicle> tempVehicles = seller.getVehicles();

        return Seller.builder().id(seller.getId()).name(tempName).phoneNumber(tempPhoneNumber).vehicles(tempVehicles).build();
    }
}

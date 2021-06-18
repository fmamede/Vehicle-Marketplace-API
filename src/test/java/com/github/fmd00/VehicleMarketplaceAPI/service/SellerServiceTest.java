package com.github.fmd00.VehicleMarketplaceAPI.service;

import com.github.fmd00.VehicleMarketplaceAPI.builder.SellerDTOBuilder;
import com.github.fmd00.VehicleMarketplaceAPI.dto.SellerDTO;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Seller;
import com.github.fmd00.VehicleMarketplaceAPI.exception.SellerNotFoundException;
import com.github.fmd00.VehicleMarketplaceAPI.mapper.SellerMapper;
import com.github.fmd00.VehicleMarketplaceAPI.repository.SellerRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {

    private static final long INVALID_SELLER_ID = 1L;

    @Mock
    private SellerRepository sellerRepository;

    private SellerMapper sellerMapper = SellerMapper.INSTANCE;

    @InjectMocks
    private SellerService sellerService;

    @Test
    void whenSellerIsInformedThenItShouldBeCreated() {
        // given
        SellerDTO expectedSellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();
        Seller expectedSavedSeller = sellerMapper.toModel(expectedSellerDTO);

        // when
        when(sellerRepository.save(expectedSavedSeller)).thenReturn(expectedSavedSeller);

        // then
        SellerDTO createdSellerDTO = sellerService.createSeller(expectedSellerDTO);

        assertThat(createdSellerDTO.getId(), is(equalTo(expectedSellerDTO.getId())));
        assertThat(createdSellerDTO.getName(), is(equalTo(expectedSellerDTO.getName())));
    }

    @Test
    void whenValidSellerIdIsGivenThenReturnASeller() throws SellerNotFoundException {
        // given
        SellerDTO expectedFoundSellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();
        Seller expectedFoundSeller = sellerMapper.toModel(expectedFoundSellerDTO);

        // when
        when(sellerRepository.findById(expectedFoundSeller.getId())).thenReturn(Optional.of(expectedFoundSeller));

        // then
        SellerDTO foundSellerDTO = sellerService.findById(expectedFoundSellerDTO.getId());

        assertThat(foundSellerDTO, is(equalTo(expectedFoundSellerDTO)));
    }

    @Test
    void whenAnUnregisteredIdIsInputThenThrowException() {
        // given
        SellerDTO expectedFoundSellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when
        when(sellerRepository.findById(expectedFoundSellerDTO.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(SellerNotFoundException.class, () -> sellerService.findById(expectedFoundSellerDTO.getId()));
    }

    @Test
    void whenListSellersIsCalledThenReturnAListOfSellers() {
        // given
        SellerDTO expectedFoundSellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();
        Seller expectedFoundSeller = sellerMapper.toModel(expectedFoundSellerDTO);

        // when
        when(sellerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundSeller));

        // then
        List<SellerDTO> foundListSellersDTO = sellerService.listAll();

        assertThat(foundListSellersDTO, is(Matchers.not(empty())));
        assertThat(foundListSellersDTO.get(0), is(equalTo(expectedFoundSellerDTO)));
    }

    @Test
    void whenListSellersIsCalledThenReturnAnEmptyListOfSellers() {
        // when
        when(sellerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        // then
        List<SellerDTO> foundListSellersDTO = sellerService.listAll();

        assertThat(foundListSellersDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenASellerShouldBeDeleted() throws SellerNotFoundException {
        // given
        SellerDTO expectedDeletedSellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();
        Seller expectedDeletedSeller = sellerMapper.toModel(expectedDeletedSellerDTO);

        // when
        when(sellerRepository.findById(expectedDeletedSellerDTO.getId())).thenReturn(Optional.of(expectedDeletedSeller));
        doNothing().when(sellerRepository).deleteById(expectedDeletedSellerDTO.getId());

        // then
        sellerService.delete(expectedDeletedSellerDTO.getId());

        verify(sellerRepository, times(1)).findById(expectedDeletedSellerDTO.getId());
        verify(sellerRepository, times(1)).deleteById(expectedDeletedSellerDTO.getId());
    }
}

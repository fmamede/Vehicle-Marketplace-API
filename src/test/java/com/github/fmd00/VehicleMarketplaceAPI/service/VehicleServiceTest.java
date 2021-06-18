package com.github.fmd00.VehicleMarketplaceAPI.service;

import com.github.fmd00.VehicleMarketplaceAPI.builder.VehicleDTOBuilder;
import com.github.fmd00.VehicleMarketplaceAPI.dto.VehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.entity.Vehicle;
import com.github.fmd00.VehicleMarketplaceAPI.exception.VehicleNotFoundException;
import com.github.fmd00.VehicleMarketplaceAPI.mapper.VehicleMapper;
import com.github.fmd00.VehicleMarketplaceAPI.repository.VehicleRepository;
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
public class VehicleServiceTest {

    private static final long INVALID_VEHICLE_ID = 1L;

    @Mock
    private VehicleRepository vehicleRepository;

    private VehicleMapper vehicleMapper = VehicleMapper.INSTANCE;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void whenVehicleIsInformedThenItShouldBeCreated() {
        // given
        VehicleDTO expectedVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedSavedVehicle = vehicleMapper.toModel(expectedVehicleDTO);

        // when
        when(vehicleRepository.save(expectedSavedVehicle)).thenReturn(expectedSavedVehicle);

        // then
        VehicleDTO createdVehicleDTO = vehicleService.createVehicle(expectedVehicleDTO);

        assertThat(createdVehicleDTO.getId(), is(equalTo(expectedVehicleDTO.getId())));
        assertThat(createdVehicleDTO.getMake(), is(equalTo(expectedVehicleDTO.getMake())));
        assertThat(createdVehicleDTO.getModel(), is(equalTo(expectedVehicleDTO.getModel())));
        assertThat(createdVehicleDTO.getYear(), is(equalTo(expectedVehicleDTO.getYear())));
        assertThat(createdVehicleDTO.getPrice(), is(equalTo(expectedVehicleDTO.getPrice())));
        assertThat(createdVehicleDTO.getMake(), is(equalTo(expectedVehicleDTO.getMake())));
    }

    @Test
    void whenValidVehicleIdIsGivenThenReturnAVehicle() throws VehicleNotFoundException {
        // given
        VehicleDTO expectedFoundVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedFoundVehicle = vehicleMapper.toModel(expectedFoundVehicleDTO);

        // when
        when(vehicleRepository.findById(expectedFoundVehicle.getId())).thenReturn(Optional.of(expectedFoundVehicle));

        // then
        VehicleDTO foundVehicleDTO = vehicleService.findById(expectedFoundVehicleDTO.getId());

        assertThat(foundVehicleDTO, is(equalTo(expectedFoundVehicleDTO)));
    }

    @Test
    void whenAnUnregisteredIdIsInputThenThrowException() {
        // given
        VehicleDTO expectedFoundVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleRepository.findById(expectedFoundVehicleDTO.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(VehicleNotFoundException.class, () -> vehicleService.findById(expectedFoundVehicleDTO.getId()));
    }

    @Test
    void whenListVehiclesIsCalledThenReturnAListOfVehicles() {
        // given
        VehicleDTO expectedFoundVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedFoundVehicle = vehicleMapper.toModel(expectedFoundVehicleDTO);

        // when
        when(vehicleRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundVehicle));

        // then
        List<VehicleDTO> foundListVehiclesDTO = vehicleService.listAll();

        assertThat(foundListVehiclesDTO, is(Matchers.not(empty())));
        assertThat(foundListVehiclesDTO.get(0), is(equalTo(expectedFoundVehicleDTO)));
    }

    @Test
    void whenListVehiclesIsCalledThenReturnAnEmptyListOfVehicles() {
        // when
        when(vehicleRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        // then
        List<VehicleDTO> foundListVehiclesDTO = vehicleService.listAll();

        assertThat(foundListVehiclesDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAVehicleShouldBeDeleted() throws VehicleNotFoundException {
        // given
        VehicleDTO expectedDeletedVehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        Vehicle expectedDeletedVehicle = vehicleMapper.toModel(expectedDeletedVehicleDTO);

        // when
        when(vehicleRepository.findById(expectedDeletedVehicleDTO.getId())).thenReturn(Optional.of(expectedDeletedVehicle));
        doNothing().when(vehicleRepository).deleteById(expectedDeletedVehicleDTO.getId());

        // then
        vehicleService.delete(expectedDeletedVehicleDTO.getId());

        verify(vehicleRepository, times(1)).findById(expectedDeletedVehicleDTO.getId());
        verify(vehicleRepository, times(1)).deleteById(expectedDeletedVehicleDTO.getId());
    }
}

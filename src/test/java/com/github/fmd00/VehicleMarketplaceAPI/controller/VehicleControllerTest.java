package com.github.fmd00.VehicleMarketplaceAPI.controller;

import com.github.fmd00.VehicleMarketplaceAPI.builder.VehicleDTOBuilder;
import com.github.fmd00.VehicleMarketplaceAPI.dto.VehicleDTO;
import com.github.fmd00.VehicleMarketplaceAPI.exception.VehicleNotFoundException;
import com.github.fmd00.VehicleMarketplaceAPI.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.github.fmd00.VehicleMarketplaceAPI.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    private static final String VEHICLE_API_URL_PATH = "/marketplace/vehicles";
    private static final long VALID_VEHICLE_ID = 1L;
    private static final long INVALID_VEHICLE_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private VehicleService vehicleService;

    @InjectMocks
    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAVehicleIsCreated() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleService.createVehicle(vehicleDTO)).thenReturn(vehicleDTO);

        // then
        mockMvc.perform(post(VEHICLE_API_URL_PATH)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vehicleDTO)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.make", is(vehicleDTO.getMake())))
                .andExpect(jsonPath("$.model", is(vehicleDTO.getModel())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();
        vehicleDTO.setMake(null);

        // then
        System.out.println(mockMvc.perform(post(VEHICLE_API_URL_PATH)

                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vehicleDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString() + "tibiano");

        System.out.println();
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleService.findById(vehicleDTO.getId())).thenReturn(vehicleDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH + "/" + vehicleDTO.getModel())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(vehicleDTO.getModel())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleService.findById(vehicleDTO.getId())).thenThrow(VehicleNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH + "/" + vehicleDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithVehiclesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleService.listAll()).thenReturn(Collections.singletonList(vehicleDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].model", is(vehicleDTO.getModel())));
    }

    @Test
    void whenGETListWithoutVehiclesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        when(vehicleService.listAll()).thenReturn(Collections.singletonList(vehicleDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(VEHICLE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        VehicleDTO vehicleDTO = VehicleDTOBuilder.builder().build().toVehicleDTO();

        // when
        doNothing().when(vehicleService).delete(vehicleDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(VEHICLE_API_URL_PATH + "/" + vehicleDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // when
        doThrow(VehicleNotFoundException.class).when(vehicleService).delete(INVALID_VEHICLE_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(VEHICLE_API_URL_PATH + "/" + INVALID_VEHICLE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

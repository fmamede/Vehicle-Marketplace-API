package com.github.fmd00.VehicleMarketplaceAPI.controller;

import com.github.fmd00.VehicleMarketplaceAPI.builder.SellerDTOBuilder;
import com.github.fmd00.VehicleMarketplaceAPI.dto.SellerDTO;
import com.github.fmd00.VehicleMarketplaceAPI.exception.SellerNotFoundException;
import com.github.fmd00.VehicleMarketplaceAPI.service.SellerService;
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
public class SellerControllerTest {

    private static final String SELLER_API_URL_PATH = "/marketplace/sellers";
    private static final long VALID_SELLER_ID = 1L;
    private static final long INVALID_SELLER_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private SellerService sellerService;

    @InjectMocks
    private SellerController sellerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sellerController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenASellerIsCreated() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when
        when(sellerService.createSeller(sellerDTO)).thenReturn(sellerDTO);

        // then
        mockMvc.perform(post(SELLER_API_URL_PATH)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sellerDTO)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(sellerDTO.getName())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();
        sellerDTO.setName(null);

        // then
        System.out.println(mockMvc.perform(post(SELLER_API_URL_PATH)

                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sellerDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString() + "tibiano");

        System.out.println();
    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when
        when(sellerService.findById(sellerDTO.getId())).thenReturn(sellerDTO);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(SELLER_API_URL_PATH + "/" + sellerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sellerDTO.getId().intValue())));
    }

    @Test
    void whenGETIsCalledWithoutRegisteredIdThenNotFoundStatusIsReturned() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when
        when(sellerService.findById(sellerDTO.getId())).thenThrow(SellerNotFoundException.class);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(SELLER_API_URL_PATH + "/" + sellerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGETListWithSellersIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when
        when(sellerService.listAll()).thenReturn(Collections.singletonList(sellerDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(SELLER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(sellerDTO.getName())));
    }

    @Test
    void whenGETListWithoutSellersIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when
        when(sellerService.listAll()).thenReturn(Collections.singletonList(sellerDTO));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(SELLER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        SellerDTO sellerDTO = SellerDTOBuilder.builder().build().toSellerDTO();

        // when

        doNothing().when(sellerService).delete(sellerDTO.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(SELLER_API_URL_PATH + "/" + sellerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws Exception {
        // when
        doThrow(SellerNotFoundException.class).when(sellerService).delete(INVALID_SELLER_ID);

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete(SELLER_API_URL_PATH + "/" + INVALID_SELLER_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

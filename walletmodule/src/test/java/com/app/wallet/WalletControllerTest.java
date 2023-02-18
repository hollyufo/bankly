package com.app.wallet;

import com.app.wallet.Service.WalletService;
import com.app.wallet.Dto.Request.walletDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WalletService walletService;

    @Test
    public void testCreateWallet() throws Exception {
        walletDto walletDto = new walletDto("12345", 100.00);
        ObjectMapper objectMapper = new ObjectMapper();
        String walletDtoJson = objectMapper.writeValueAsString(walletDto);

        mockMvc.perform(post("http://localhost:8081/api/v1/wallet/get/va137909")
                .contentType(MediaType.APPLICATION_JSON)
                .content(walletDtoJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message", is("Wallet created successfully")))
            .andExpect(jsonPath("$.wallet.govId", is("12345")))
            .andExpect(jsonPath("$.wallet.balance", is(100.00)));
    }

    @Test
    public void testGetWallet() throws Exception {
        String govId = "12345";
        walletService.createWallet(govId, 100.00);

        mockMvc.perform(get("http://localhost:8081/api/v1/wallet/get/{govId}", govId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is("Wallet retrieved successfully")))
            .andExpect(jsonPath("$.wallet.govId", is("12345")))
            .andExpect(jsonPath("$.wallet.balance", is(100.00)));
    }

    @Test
    public void testUpdateWallet() throws Exception {
        String govId = "12345";
        walletService.createWallet(govId, 100.00);

        mockMvc.perform(put("/update/{govId}/{balance}", govId, 200.00))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is("Wallet balance updated successfully")))
            .andExpect(jsonPath("$.wallet.govId", is("12345")))
            .andExpect(jsonPath("$.wallet.balance", is(200.00)));
    }
}

package com.sapient.creditcard.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CreditCardIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CreditCardRepository creditCardRepository;

    long creditCardNumber = 61789372994L;
    BigDecimal limit = new BigDecimal("1000.50");

    CreditCard card = CreditCard.builder()
            .creditAccountId("123")
            .cardNumber(creditCardNumber)
            .name("Hema")
            .limit(limit)
            .build();

    @Test
    void test_Credit_add_endpoint_success() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/add")
                .content(asJsonString(card))
                .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void test_Credit_getAll_endpoint_success() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/getAll")
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }


    public static String asJsonString(final Object object) {

        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

}

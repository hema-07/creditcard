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
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CreditCardIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CreditCardRepository creditCardRepository;

    BigInteger creditCardNumber = new BigInteger("61789372994");
    BigDecimal limit = new BigDecimal("1000.50");

    CreditCard card = CreditCard.builder()
            .creditAccountId("123")
            .cardNumber(creditCardNumber)
            .name("Hema")
            .limit(limit)
            .build();

    CreditCard failureCard = CreditCard.builder()
            .creditAccountId("123")
            .cardNumber(creditCardNumber)
            .name("Hema")
            .limit(limit)
            .build();

    CreditCard missedValueCard = CreditCard.builder()
            .creditAccountId("123")
            .cardNumber(creditCardNumber)
            .limit(limit)
            .build();

    @Test
    public void test_Credit_add_endpoint_success() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/creditcard")
                        .header("Authorization","Basic YWRtaW46YWRtaW4=")
                .content(asJsonString(card))
                .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void test_add_endpoint_for_existing_card() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/creditcard")
                        .header("Content-Type","application/json")
                        .header("Authorization","Basic YWRtaW46YWRtaW4=")
                        .content(asJsonString(card))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        ResultActions resultActions1 = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/creditcard")
                        .header("Authorization","Basic YWRtaW46YWRtaW4=")
                        .content(asJsonString(card))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions1.andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }

    @Test
    public void test_add_endpoint_for_not_valid_card() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/creditcard")
                        .header("Authorization","Basic YWRtaW46YWRtaW4=")
                        .content(asJsonString(failureCard))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isInternalServerError());

    }

    @Test
    public void test_add_endpoint_for_missing_card_details() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.post("/creditcard")
                        .header("Authorization","Basic YWRtaW46YWRtaW4=")
                        .content(asJsonString(missedValueCard))
                        .contentType(MediaType.APPLICATION_JSON)
        );
        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void test_get_all_endpoint_for_success_scenario() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(
                MockMvcRequestBuilders.get("/creditcard")
                        .header("Authorization","Basic YWRtaW46YWRtaW4=")
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

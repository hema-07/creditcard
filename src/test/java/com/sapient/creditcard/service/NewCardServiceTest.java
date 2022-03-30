package com.sapient.creditcard.service;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.repository.CreditCardRepository;
import org.hibernate.JDBCException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewCardServiceTest {

    @Autowired
    private NewCardService newCardService;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @Test
    public void addNewCard_success_scenario() throws CreditCardApplicationException {
        CreditCardRequest request = CreditCardRequest.builder()
                .cardNumber(49927398716L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        CreditCard expectedCard = CreditCard.builder()
                .cardNumber(49927398716L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Mockito.when(creditCardRepository.save(expectedCard)).thenReturn(expectedCard);
        CreditCard actualCreditCard = newCardService.addNewCard(request);
        assertEquals(expectedCard.getCardNumber(), actualCreditCard.getCardNumber());
    }

    @Test
    public void findExistingCardDetails_failure_scenario() throws CreditCardApplicationException {
        CreditCardRequest request = CreditCardRequest.builder()
                .cardNumber(49927398716L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        CreditCard card = CreditCard.builder()
                .cardNumber(49927398716L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        List<CreditCard> list = new ArrayList<>();
        list.add(card);
        creditCardRepository.save(card);
        Mockito.when(creditCardRepository.findByCardNumber(card.getCardNumber(), card.getName())).thenReturn(list);
        boolean existingCardDetails = newCardService.findExistingCardDetails(request);
        assertEquals(false, existingCardDetails);
    }

    @Test
    public void findExistingCardDetails_success_scenario() {
        CreditCardRequest card = CreditCardRequest.builder()
                .cardNumber(49927398716L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Mockito.when(creditCardRepository.findByCardNumber(card.getCardNumber(), card.getName())).thenReturn(Collections.emptyList());
        List<CreditCard> creditCard = creditCardRepository.findByCardNumber(card.getCardNumber(),"Hema");
        assertEquals(true, creditCard.isEmpty());
    }

}
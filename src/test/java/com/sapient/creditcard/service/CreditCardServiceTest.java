package com.sapient.creditcard.service;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.validator.CreditCardLuhnValidation;
import com.sapient.creditcard.validator.ValidationResult;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static com.sapient.creditcard.util.Constants.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class CreditCardServiceTest {

    @MockBean
    private NewCardService newCardService;

    @MockBean
    private ViewCardService viewCardService;

    @MockBean
    private CreditCardLuhnValidation creditCardLuhnValidation;

    @Autowired
    private CreditCardService creditCardService;

    @Test
    public void validateRequest() {
        CreditCardRequest card = CreditCardRequest.builder()
                .cardNumber(61789372994L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        ValidationResult validate = creditCardService.validateRequest(card);
        assertEquals(true, validate.isValid());
    }

    @Test
    public void addNewCardDetails_success_scenario() throws CreditCardApplicationException, ErrorResponse {
        CreditCard card = CreditCard.builder()
                .cardNumber(61789372994L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        CreditCardRequest request = CreditCardRequest.builder()
                .cardNumber(61789372994L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Mockito.when(creditCardLuhnValidation.checkCardNumberUsingLuhn(card.getCardNumber())).thenReturn(true);
        Mockito.when(newCardService.findExistingCardDetails(request)).thenReturn(true);
        Mockito.when(newCardService.addNewCard(request)).thenReturn(card);
        CreditCard actualNewCardDetails = creditCardService.addNewCardDetails(request);
        Assert.assertEquals(card.getCardNumber(), actualNewCardDetails.getCardNumber());
    }

    @Test
    public void addNewCardDetails_failure_scenario() throws CreditCardApplicationException, ErrorResponse {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(49924654398715L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        CreditCardRequest request = CreditCardRequest.builder()
                .cardNumber(49924654398715L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Mockito.when(creditCardLuhnValidation.checkCardNumberUsingLuhn(card.getCardNumber())).thenReturn(true);
        Mockito.when(newCardService.findExistingCardDetails(request)).thenReturn(true);
        creditCardService.addNewCardDetails(request);
        Assert.assertNotSame(new ErrorResponse(EXISTING_CARD, EXISTING_CARD_DESC), card);
    }

    @Test
    public void getAllCreditCardList_success() throws CreditCardApplicationException, ErrorResponse {
        CreditCard card = CreditCard.builder()
                .cardNumber(61789372994L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();

        List<CreditCard> allCreditCardDetails = new ArrayList<>();
        allCreditCardDetails.add(card);
        Mockito.when(viewCardService.getAllCreditCardDetails()).thenReturn(allCreditCardDetails);
        List<CreditCard> actualCreditCardDetails = viewCardService.getAllCreditCardDetails();
        Assert.assertEquals(1, actualCreditCardDetails.size());
    }

}
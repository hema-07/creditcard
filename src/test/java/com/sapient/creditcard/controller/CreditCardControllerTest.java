package com.sapient.creditcard.controller;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.service.CreditCardService;
import com.sapient.creditcard.validator.ValidationResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardControllerTest {

    @Autowired
    private CreditCardController creditCardController;

    @MockBean
    private CreditCardService creditCardService;

    long creditCardNumber = 61789372994L;
    BigDecimal limit = new BigDecimal("1000");


    @Test
    public void addCreditCard_success_scenario() throws ErrorResponse, CreditCardApplicationException {
        CreditCardRequest creditCardRequest = CreditCardRequest.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        CreditCard creditCard = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Mockito.when(creditCardService.validateRequest(creditCardRequest)).thenReturn(new ValidationResult(true, creditCardRequest));
        Mockito.when(creditCardService.addNewCardDetails(creditCardRequest)).thenReturn(creditCard);

        CreditCard actualCreditCard = creditCardService.addNewCardDetails(creditCardRequest);
        Assert.assertEquals(creditCardNumber, actualCreditCard.getCardNumber());
        ResponseEntity<Object> actualResponseEntity = creditCardController.addCreditCard(creditCardRequest);
        Assert.assertEquals(HttpStatus.CREATED, actualResponseEntity.getStatusCode());
    }

    @Test
    public void addCreditCard_failure_scenario() throws ErrorResponse, CreditCardApplicationException {
        CreditCardRequest creditCardRequest = CreditCardRequest.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        CreditCard creditCard = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Mockito.when(creditCardService.validateRequest(creditCardRequest)).thenReturn(new ValidationResult(false, creditCardRequest));
        Mockito.when(creditCardService.addNewCardDetails(creditCardRequest)).thenReturn(creditCard);

        CreditCard actualCreditCard = creditCardService.addNewCardDetails(creditCardRequest);
        Assert.assertEquals(creditCardNumber, actualCreditCard.getCardNumber());
        ResponseEntity<Object> actualResponseEntity = creditCardController.addCreditCard(creditCardRequest);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actualResponseEntity.getStatusCode());
    }

    @Test
    public void getAllCreditCard_success_scenario() throws ErrorResponse, CreditCardApplicationException {
        CreditCard creditCard = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        List<CreditCard> list = new ArrayList<>();
        list.add(creditCard);
        Mockito.when(creditCardService.getAllCreditCardDetails()).thenReturn(list);
        ResponseEntity<Object> actualResponseEntity = creditCardController.getAllCreditCard();
        Assert.assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
    }
}
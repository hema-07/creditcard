package com.sapient.creditcard.controller;

import com.sapient.creditcard.entity.CreditCard;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardControllerTest {

    @Autowired
    private CreditCardController creditCardController;

    BigInteger creditCardNumber = new BigInteger("61789372994");
    BigDecimal limit = new BigDecimal("1000");

    @Test
    public void addCreditCard_success_scenario() {
        CreditCard card = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(limit)
                .build();
        ResponseEntity<?> responseEntity = creditCardController.addCreditCard(card);
//        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

    @Test
    public void add_existing_credit_card_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(limit)
                .build();
        creditCardController.addCreditCard(card);
        ResponseEntity<?> responseEntity1 = creditCardController.addCreditCard(card);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity1.getStatusCode());

    }

    @Test
    public void add_wrong_credit_card_for_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .cardNumber(new BigInteger("122334457567656"))
                .name("Hema")
                .limit(limit)
                .build();
        ResponseEntity<?> responseEntity = creditCardController.addCreditCard(card);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

    }

    @Test
    public void add_missing_credit_card_number_for_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .name("Hema")
                .limit(limit)
                .build();
        ResponseEntity<?> responseEntity = creditCardController.addCreditCard(card);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void add_missing_credit_card_holder_name_scenario() {
        CreditCard card = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .limit(limit)
                .build();
        ResponseEntity<?> responseEntity = creditCardController.addCreditCard(card);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void add_missing_credit_card_limit_scenario() {
        CreditCard card = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .build();
        ResponseEntity<?> responseEntity = creditCardController.addCreditCard(card);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }

    @Test
    public void getAllCreditCard() {
        CreditCard card = CreditCard.builder()
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(limit)
                .build();
        creditCardController.addCreditCard(card);
        ResponseEntity<?> responseEntity1 = creditCardController.getAllCreditCard();
        Assert.assertEquals(HttpStatus.OK, responseEntity1.getStatusCode());
    }
}
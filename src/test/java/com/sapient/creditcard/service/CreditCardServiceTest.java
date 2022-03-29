package com.sapient.creditcard.service;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.repository.CreditCardRepository;
import com.sapient.creditcard.validator.ValidationResult;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardServiceTest {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CreditCardRepository creditCardRepository;
    
    @Test
    public void addNewCard_success_scenario() throws CreditCardApplicationException {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("49927398716"))
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        creditCardService.addNewCard(card);
        Optional<CreditCard> creditCard = creditCardRepository.findById("123");
        assertEquals(true, creditCard.isEmpty());
    }

    @Test
    public void addNewCard_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("49927398716"))
                .limit(new BigDecimal("12949835.00"))
                .build();
        List<CreditCard> creditCard = creditCardRepository.findByCardNumber(new BigInteger("2"),"Hema");
        assertEquals(false, creditCard.isEmpty());
    }

    @Test
    public void findCreditCard_success_scenario() throws CreditCardApplicationException {
        CreditCard card = CreditCard.builder()
                .cardNumber(new BigInteger("61789372994"))
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        creditCardService.addNewCard(card);
        Boolean isValid = creditCardService.findCreditCard(card);
        assertEquals(false, isValid);
    }

    @Test
    public void findCreditCard_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("49924654398715"))
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        Boolean isValid = creditCardService.findCreditCard(card);
        assertEquals(true, isValid);
    }

    @Test
    public void getAllCreditCardList_success() throws CreditCardApplicationException {
        CreditCard card = CreditCard.builder()
                .cardNumber(new BigInteger("61789372994"))
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        creditCardService.addNewCard(card);
        List<CreditCard> allCreditCardList = creditCardService.getAllCreditCardList();
        assertEquals(false, allCreditCardList.isEmpty());

    }

    @Test
    public void getAllCreditCardList_failure() throws CreditCardApplicationException {
        creditCardRepository.deleteAll();
        List<CreditCard> allCreditCardList = creditCardService.getAllCreditCardList();
        assertEquals(0, allCreditCardList.size());

    }

    @Test
    public void validate() {
        CreditCard card = CreditCard.builder()
                .cardNumber(new BigInteger("61789372994"))
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        ValidationResult validate = creditCardService.validate(card);
        assertEquals(true, validate.isValid());
    }
}
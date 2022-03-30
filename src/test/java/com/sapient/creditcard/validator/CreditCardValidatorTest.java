package com.sapient.creditcard.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigInteger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardValidatorTest {

    @Autowired
    private CreditCardLuhnValidation creditCardValidator;

    @Test
    public void validateCreditCard_success_scenario() {
        long creditCardNumber = 61789372994L;
        boolean validateCreditCard = creditCardValidator.checkCardNumberUsingLuhn(creditCardNumber);
        Assert.assertEquals(true, validateCreditCard);
    }

    @Test
    public void validateCreditCard_failure_scenario() {
        long creditCardNumber = 617893785872994L;
        boolean validateCreditCard = creditCardValidator.checkCardNumberUsingLuhn(creditCardNumber);
        Assert.assertEquals(false, validateCreditCard);
    }
}
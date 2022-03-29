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
    private CreditCardValidator creditCardValidator;

    @Test
    public void validateCreditCard_success_scenario() {
        BigInteger creditCardNumber = new BigInteger("61789372994");
        boolean validateCreditCard = creditCardValidator.validateCreditCard(creditCardNumber);
        Assert.assertEquals(true, validateCreditCard);
    }

    @Test
    public void validateCreditCard_failure_scenario() {
        BigInteger creditCardNumber = new BigInteger("61789372997");
        boolean validateCreditCard = creditCardValidator.validateCreditCard(creditCardNumber);
        Assert.assertEquals(false, validateCreditCard);
    }
}
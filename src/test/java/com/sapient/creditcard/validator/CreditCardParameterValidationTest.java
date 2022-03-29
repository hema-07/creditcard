package com.sapient.creditcard.validator;

import com.sapient.creditcard.entity.CreditCard;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardParameterValidationTest {

    BigInteger creditCardNumber = new BigInteger("61789372994");

    @Autowired
    CreditCardParameterValidation creditCardParameterValidation;

    @Test
    public void validate_success_scenario() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(creditCardNumber)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        ValidationResult validate = creditCardParameterValidation.validate(card);
        Assert.assertEquals(true, validate.isValid());

    }

    @Test
    public void validate_card_number_missing_scenario() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        ValidationResult validate = creditCardParameterValidation.validate(card);
        Assert.assertEquals(false, validate.isValid());

    }

    @Test
    public void validate_card_number_length_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("1298446837638763846394839486369483"))
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        ValidationResult validate = creditCardParameterValidation.validate(card);
        Assert.assertEquals(false, validate.isValid());

    }

    @Test
    public void validate_card_limit_failure_scenario() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("61789372994"))
                .name("Hema")
                .build();
        ValidationResult validate = creditCardParameterValidation.validate(card);
        Assert.assertEquals(false, validate.isValid());

    }

    @Test
    public void validate_card_limit_failure_scenario_maxlength() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("61789372994"))
                .name("Hema")
                .limit(new BigDecimal("1294983547835638767567462938454392800"))
                .build();
        ValidationResult validate = creditCardParameterValidation.validate(card);
        Assert.assertEquals(false, validate.isValid());

    }

    @Test
    public void validate_card_name_failure_scenario_maxlength() {
        CreditCard card = CreditCard.builder()
                .creditAccountId("123")
                .cardNumber(new BigInteger("61789372994"))
                .name("HemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHemaHema")
                .limit(new BigDecimal("12949835.00"))
                .build();
        ValidationResult validate = creditCardParameterValidation.validate(card);
        Assert.assertEquals(false, validate.isValid());

    }

}
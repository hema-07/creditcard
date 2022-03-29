package com.sapient.creditcard.validator;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Arrays;

@Component
public class CreditCardValidator {

    /**
     * This method validates credit card and returns true for success and false for invalid credit card.
     * @param creditCard After validated request, credit card number enters here
     * @return valid card number: true; invalid card number: false
     */
    public boolean validateCreditCard(BigInteger creditCard) {

        String creditCard1 = String.valueOf(creditCard);
        int cLength = creditCard1.length();

        int[] cIntArray = new int[cLength];

        for (int i = 0; i < cLength; i++) {
            cIntArray[i] = Integer.parseInt(creditCard1.charAt(i) + "");
        }

        for (int i = cLength - 2; i >= 0; i = i - 2) {
            int number = cIntArray[i];
            number = number * 2;
            if (number > 9) {
                number = number % 10 + number / 10;
            }
            cIntArray[i] = number;
        }
        int sum = Arrays.stream(cIntArray).sum();

        if (sum % 10 == 0) {
            return true;
        }

        return false;

    }



}


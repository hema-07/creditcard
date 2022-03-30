package com.sapient.creditcard.validator;

import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class CreditCardLuhnValidation {

    /**
     * This method validates credit card and returns true for success and false for invalid credit card.
     * @param creditCardNumber After validated request, credit card number enters here
     * @return valid card number: true; invalid card number: false
     */
    public boolean checkCardNumberUsingLuhn(long creditCardNumber) {

        String cardNumber = String.valueOf(creditCardNumber);

        int cLength = cardNumber.length();

        int[] cIntArray = new int[cLength];

        for (int i = 0; i < cLength; i++) {
            cIntArray[i] = Integer.parseInt(cardNumber.charAt(i) + "");
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

        return sum % 10 == 0;

    }

}


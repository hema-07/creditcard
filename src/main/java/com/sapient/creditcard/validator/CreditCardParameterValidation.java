package com.sapient.creditcard.validator;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.util.Constants;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

import static com.sapient.creditcard.util.Constants.*;
import static java.lang.String.*;

@Component
public class CreditCardParameterValidation implements Validator{

    /**
     * This method validates the Request body
     * @param creditCard unvalidated request object from controller
     * @return when the invalid request enters, it returns boolean value false with error code.
     * When the valid request enters, it returns boolean true with validated Credit card object.
     */
    @Override
    public ValidationResult validate(CreditCard creditCard) {

        if (creditCard.getName()==null || creditCard.getName().equals("") || creditCard.getName().isBlank()) {
            return new ValidationResult(false, missingCreditCardHolderName, missingCreditCardHolderNameDetails);
        }
        if (creditCard.getName().length() > 25) {
            return new ValidationResult(false, Constants.mismatchLengthCreditCardHolderName, mismatchLengthCreditCardHolderNameDetails);
        }
        if (creditCard.getCardNumber() == null || valueOf(creditCard.getCardNumber()).equals("")) {
            return new ValidationResult(false, missingCreditCardNumber, missingCreditCardNumberDetails);
        }
        if (valueOf(creditCard.getCardNumber()).length() > 19) {
            return new ValidationResult(false, mismatchlengthCreditCardNumber, mismatchlengthCreditCardNumberDetails);
        }
        if (creditCard.getLimit() == null || valueOf(creditCard.getLimit()).equals("")) {
            return new ValidationResult(false, missingCreditCardLimit, missingCreditCardLimitDetails);
        }
        if (valueOf(creditCard.getLimit()).length() > 15) {
            return new ValidationResult(false, Constants.mismatchlengthCreditCardLimit, mismatchlengthCreditCardLimitDetails);
        }
        return new ValidationResult(true, creditCard);
    }
}

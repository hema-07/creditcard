package com.sapient.creditcard.validator;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import org.springframework.stereotype.Component;
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
    public ValidationResult validate(CreditCardRequest creditCard) {

        if (creditCard.getName()==null || creditCard.getName().equals("") || creditCard.getName().isBlank()) {
            return new ValidationResult(false, MISSING_LENGTH_CARD_HOLDER_NAME, MISSING_LENGTH_CARD_HOLDER_NAME_DESC);
        }
        if (creditCard.getName().length() > 25) {
            return new ValidationResult(false, MISMATCH_LENGTH_CARD_HOLDER_NAME, MISMATCH_LENGTH_CARD_HOLDER_NAME_DESC);
        }
        if (valueOf(creditCard.getCardNumber()).equals("") || valueOf(creditCard.getCardNumber()).isBlank() || creditCard.getCardNumber() == 0) {
            return new ValidationResult(false, MISSING_CARD_NUMBER, MISSING_CARD_NUMBER_DESC);
        }
        if (valueOf(creditCard.getCardNumber()).length() > 19) {
            return new ValidationResult(false, MISMATCH_LENGTH_CARD_NUMBER, MISMATCH_LENGTH_CARD_NUMBER_DESC);
        }
        if (creditCard.getLimit() == null || valueOf(creditCard.getLimit()).equals("")) {
            return new ValidationResult(false, MISSING_CARD_LIMIT, MISSING_CARD_LIMIT_DESC);
        }
        if (valueOf(creditCard.getLimit()).length() > 15) {
            return new ValidationResult(false, MISMATCH_CARD_LIMIT, MISMATCH_CARD_LIMIT_DESC);
        }
        return new ValidationResult(true, creditCard);
    }
}

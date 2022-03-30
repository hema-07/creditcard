package com.sapient.creditcard.validator;

import com.sapient.creditcard.controller.dto.CreditCardRequest;

public interface Validator {

    ValidationResult validate(CreditCardRequest creditCardRequest);
}

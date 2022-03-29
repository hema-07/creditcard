package com.sapient.creditcard.validator;

import com.sapient.creditcard.entity.CreditCard;

public interface Validator {

    ValidationResult validate(CreditCard creditCard);
}

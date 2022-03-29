package com.sapient.creditcard.validator;

import com.sapient.creditcard.entity.CreditCard;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResult {

    private boolean isValid;
    private String message;
    private String errorCode;
    private CreditCard creditCard;

    public ValidationResult(boolean isValid, CreditCard creditCard) {
        this.isValid = isValid;
        this.creditCard = creditCard;
    }

    public ValidationResult(boolean isValid, String message, String errorCode) {
        this.isValid = isValid;
        this.message = message;
        this.errorCode = errorCode;
    }
}

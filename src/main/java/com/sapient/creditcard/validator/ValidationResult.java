package com.sapient.creditcard.validator;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
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
    private CreditCardRequest creditCardRequest;

    public ValidationResult(boolean isValid, CreditCardRequest creditCardRequest) {
        this.isValid = isValid;
        this.creditCardRequest = creditCardRequest;
    }

    public ValidationResult(boolean isValid, String message, String errorCode) {
        this.isValid = isValid;
        this.message = message;
        this.errorCode = errorCode;
    }
}

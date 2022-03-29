package com.sapient.creditcard.exception;

import com.sapient.creditcard.modal.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@AllArgsConstructor
public class CreditCardApplicationException extends Exception {

    private static final long serialVersionId = 1L;
    private ErrorResponse errorResponse;
    private HttpStatus httpStatus;

    public CreditCardApplicationException(String exception) { super(exception);}
}

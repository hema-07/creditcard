package com.sapient.creditcard.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreditCardApplicationException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String exception;
    private final String message;

}

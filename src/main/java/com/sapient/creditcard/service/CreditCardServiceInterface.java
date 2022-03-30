package com.sapient.creditcard.service;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.ErrorResponse;

import java.util.List;

public interface CreditCardServiceInterface {

    CreditCard addNewCardDetails(CreditCardRequest request) throws CreditCardApplicationException, ErrorResponse;

    List<CreditCard> getAllCreditCardDetails() throws CreditCardApplicationException, ErrorResponse;

}

package com.sapient.creditcard.service;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.validator.CreditCardLuhnValidation;
import com.sapient.creditcard.validator.CreditCardParameterValidation;
import com.sapient.creditcard.validator.ValidationResult;
import com.sapient.creditcard.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.sapient.creditcard.util.Constants.*;

@Service
public class CreditCardService implements CreditCardServiceInterface {

    @Autowired
    private NewCardService newCardService;

    @Autowired
    private ViewCardService viewCardService;

    @Autowired
    private CreditCardLuhnValidation creditCardLuhnValidation;

    @Autowired
    private Validator[] validators;

    private static final Logger logger = LogManager.getLogger(CreditCardService.class);

    @Override
    public CreditCard addNewCardDetails(CreditCardRequest request) throws CreditCardApplicationException, ErrorResponse {

        boolean isValidCard = creditCardLuhnValidation.checkCardNumberUsingLuhn(request.getCardNumber());

        if (!isValidCard) {

            logger.debug("Credit card didn't pass Luhn Algorithm");

            throw new ErrorResponse(CARD_NUMBER_NOT_VALID, CARD_NUMBER_NOT_VALID_DESC);
        }

        boolean findExistingCard = newCardService.findExistingCardDetails(request);

        if (!findExistingCard) {

            logger.debug("Found Existing card ");

            throw new ErrorResponse(EXISTING_CARD, EXISTING_CARD_DESC);

        }
        logger.debug("Credit card is not existed in system, passed Luhn Algorithm and creating new card");

        return newCardService.addNewCard(request);
    }

    /**
     * This method will return list of credit cards from DB.
     *
     * @return it returns list of credit cards
     */
    @Override
    public List<CreditCard> getAllCreditCardDetails() throws CreditCardApplicationException, ErrorResponse {

        return viewCardService.getAllCreditCardDetails();
    }

    /* This method will validate the Credit card object.
     * @param creditCard this param is a request body from endpoint
     * @return ValidationResult returns a boolean value (valid request: true; invalid request: false) with object if it is valid.
     * invalid result will return boolean value false with error code and description
     */
    public ValidationResult validateRequest(CreditCardRequest creditCard) {
        CreditCardRequest validatedCreditCard = null;

        for (Validator validator : this.validators) {
            ValidationResult validationResult = validator.validate(creditCard);

            if (validator instanceof CreditCardParameterValidation) {
                validatedCreditCard = validationResult.getCreditCardRequest();
            }
            if (!validationResult.isValid()) {
                return validationResult;
            }
        }
        return new ValidationResult(true, validatedCreditCard);
    }

}



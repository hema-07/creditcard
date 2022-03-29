package com.sapient.creditcard.controller;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.CreditCardResponse;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.service.CreditCardService;
import com.sapient.creditcard.util.Constants;
import com.sapient.creditcard.validator.CreditCardValidator;
import com.sapient.creditcard.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 *
 */
@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private CreditCardValidator creditCardValidator;

    private static final Logger logger = LogManager.getLogger(CreditCardController.class);

    /**
     *This method validates the request and find it from db. if it is new valid detail, it will store it in DB.
     * @param creditCard it has 3 params : name, card number and limit.
     * @return validated request will save into db, invalid request will throw an error response.
     * when entered credit card is already present in db, it will throw an error response with errorCode.
     */
    @RequestMapping(value = "/add", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> addCreditCard(@RequestBody CreditCard creditCard)  {

        ValidationResult validationResult = creditCardService.validate(creditCard);

        if (!validationResult.isValid()) {
            return new ResponseEntity<>(validationResult, HttpStatus.BAD_REQUEST);
        }

        boolean isValidCard = creditCardValidator.validateCreditCard(creditCard.getCardNumber());

        if (isValidCard) {

            Boolean checkExistingCard = creditCardService.findCreditCard(creditCard);

            if (checkExistingCard.equals(true)) {

                try {
                    creditCardService.addNewCard(creditCard);

                } catch (CreditCardApplicationException e) {
                    return new ResponseEntity<>(e.getErrorResponse(),e.getHttpStatus());
                }

                logger.debug("new credit card added to db");

                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {

                ErrorResponse errorResponse = ErrorResponse.builder()
                            .errorCode(Constants.existingCard)
                            .errorDescription(Constants.existingCardDescription)
                            .build();
                logger.debug("entered credit card is already present in db");
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {

                ErrorResponse errorResponse = ErrorResponse.builder()
                        .errorCode(Constants.cardNumberNotValid)
                        .errorDescription(Constants.cardNumberNotValidDescription)
                        .build();
                logger.debug("Credit card is not valid");
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }


    /**
     * This method will fetch all the Credit card from DB.
     * @return list of credit cards and error response will throw an error message when there is no data in DB.
     */
    @RequestMapping(value = "/getAll", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> getAllCreditCard() {

        try {
            List<CreditCard> allCreditCardList = creditCardService.getAllCreditCardList();
            if (!allCreditCardList.isEmpty()) {

                CreditCardResponse creditCardResponse = CreditCardResponse.builder()
                        .message("Card Details has been collected from DB")
                        .body(allCreditCardList).build();
                logger.debug("successfully fetched data from DB");
                return new ResponseEntity<>(creditCardResponse, HttpStatus.OK);
            } else {

                ErrorResponse errorResponse = ErrorResponse.builder()
                        .errorCode(Constants.noDataFromDB)
                        .errorDescription(Constants.noDataFromDBDetails)
                        .build();
                logger.debug("error occured while fetching from db");
                return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
            }
        } catch (CreditCardApplicationException ex) {
            return new ResponseEntity<>(ex.getErrorResponse(),ex.getHttpStatus());
        }

    }

}

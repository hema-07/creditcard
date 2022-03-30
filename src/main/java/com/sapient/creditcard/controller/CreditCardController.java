package com.sapient.creditcard.controller;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.CreditCardResponse;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.service.CreditCardService;
import com.sapient.creditcard.validator.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.http.MediaType.*;

@RestController
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    private static final Logger logger = LogManager.getLogger(CreditCardController.class);

    /**
     *This method validates the request and find it from db. if it is new valid detail, it will store it in DB.
     * @param request it has 3 params : name, card number and limit.
     * @return validated request will save into db, invalid request will throw an error response.
     * when entered credit card is already present in db, it will throw an error response with errorCode.
     */
    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCreditCard(@RequestBody CreditCardRequest request) throws CreditCardApplicationException, ErrorResponse {

        ValidationResult validationResult = creditCardService.validateRequest(request);

        if (!validationResult.isValid()) {
            return new ResponseEntity<>(validationResult, HttpStatus.BAD_REQUEST);
        }

        CreditCard newCardDetails = creditCardService.addNewCardDetails(request);

        CreditCardResponse creditCardResponse = CreditCardResponse.builder()
                .message("Card Details has been added")
                .body(newCardDetails).build();

        logger.debug("Added New Card");

        return new ResponseEntity<>(creditCardResponse, HttpStatus.CREATED);

    }

    /**
     * This method will fetch all the Credit card from DB.
     * @return list of credit cards and error response will throw an error message when there is no data in DB.
     */
    @GetMapping(value = "/getAll", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllCreditCard() throws CreditCardApplicationException, ErrorResponse {

            List<CreditCard> allCreditCardList = creditCardService.getAllCreditCardDetails();

            CreditCardResponse creditCardResponse = CreditCardResponse.builder()
                        .message("Card Details has been collected from DB")
                        .body(allCreditCardList).build();

            logger.debug("successfully fetched data from DB");

            return new ResponseEntity<>(creditCardResponse, HttpStatus.OK);
    }

}

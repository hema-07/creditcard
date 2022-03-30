package com.sapient.creditcard.service;

import com.sapient.creditcard.controller.dto.CreditCardRequest;
import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.repository.CreditCardRepository;
import com.sapient.creditcard.util.Constants;
import com.sapient.creditcard.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class NewCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    private static final Logger logger = LogManager.getLogger(NewCardService.class);



    /**
     *This method will find the credit card from DB.
     * @param creditCardRequest this request is a validated response from Validator.
     * @return true or false
     * Credit card object is from controller. This method helps to find credit card object from DB.
     */
    public boolean findExistingCardDetails(CreditCardRequest creditCardRequest) throws CreditCardApplicationException {

        try {
            List<CreditCard> findCreditCard = creditCardRepository.findByCardNumber(creditCardRequest.getCardNumber(), creditCardRequest.getName());

            return findCreditCard.isEmpty();

        } catch (JDBCException e) {

            throw new CreditCardApplicationException(Constants.JDBC_EXCEPTION_DESC, e.getMessage());
        }

    }


    /**
     * this method will save validated credit card object to DB
     * @param creditCardRequest validated result from Validator
     * @return card
     */
    public CreditCard addNewCard(CreditCardRequest creditCardRequest) throws CreditCardApplicationException {

        CreditCard card = new CreditCard();
        card.setCardNumber(creditCardRequest.getCardNumber());
        card.setName(creditCardRequest.getName());
        card.setLimit(creditCardRequest.getLimit());
        card.setCreditAccountId(getRandomNumberGenerator());
        card.setBalance(new BigDecimal("0"));

        try {

            creditCardRepository.save(card);

        } catch (JDBCException e) {

            logger.debug("Exception : {}",e.getLocalizedMessage());
            throw new CreditCardApplicationException(String.valueOf(e.getSQLException()), e.getLocalizedMessage());

        }
        return card;
    }

    /**
     * random UUID generator method
     * @return string value - UUID for creditCard unique Id
     */
    public static String getRandomNumberGenerator() {

        return String.valueOf(UUID.randomUUID());
    }

}

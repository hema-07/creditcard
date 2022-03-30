package com.sapient.creditcard.service;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.repository.CreditCardRepository;
import com.sapient.creditcard.util.Constants;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

import static com.sapient.creditcard.util.Constants.*;

@Component
public class ViewCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;


    /**
     * This method will return list of credit cards from DB.
     *
     * @return it returns list of credit cards
     */
    public List<CreditCard> getAllCreditCardDetails() throws CreditCardApplicationException, ErrorResponse {

        try {
            List<CreditCard> creditCards = (List<CreditCard>) creditCardRepository.findAll();

            if (creditCards.isEmpty()) {

                throw new ErrorResponse(NO_DATA_FROM_DB, NO_DATA_FROM_DB_DESC);
            }
            return creditCards;

        } catch (JDBCException e) {
                throw new CreditCardApplicationException(String.valueOf(e.getSQLException()), e.getLocalizedMessage());
        }

    }
}

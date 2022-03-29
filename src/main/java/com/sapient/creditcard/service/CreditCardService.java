package com.sapient.creditcard.service;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.repository.CreditCardRepository;
import com.sapient.creditcard.validator.CreditCardParameterValidation;
import com.sapient.creditcard.validator.ValidationResult;
import com.sapient.creditcard.validator.Validator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private Validator[] validators;

    /**
     * this method will save validated credit card object to DB
     * @param creditCard validated result from Validator
     */
    public void addNewCard(CreditCard creditCard) throws CreditCardApplicationException{

        creditCard.setCreditAccountId(getRandomNumberGenerator());
        creditCard.setBalance(new BigDecimal("0"));

        creditCardRepository.save(creditCard);

    }

    /**
     * random UUID generator method
     * @return string value - UUID for creditCard unique Id
     */
    public static String getRandomNumberGenerator() {

        String uuid = String.valueOf(UUID.randomUUID());

        return uuid;
    }

    /**
     *This method will find the credit card from DB.
     * @param creditCard this request is a validated response from Validator.
     * @return true or false
     * Credit card object is from controller. This method helps to find credit card object from DB.
     */
    public Boolean findCreditCard(CreditCard creditCard) {

            List<CreditCard> findCreditCard = creditCardRepository.findByCardNumber(creditCard.getCardNumber(), creditCard.getName());

            if (findCreditCard.isEmpty()) {
                return true;

            } else {
                return false;
            }

    }


    /**
     *This method will return list of credit cards from DB.
     * @return it returns list of credit cards
     */
    public List<CreditCard> getAllCreditCardList() throws CreditCardApplicationException{

        Iterable<CreditCard> creditCards = creditCardRepository.findAll();

        return (List<CreditCard>) creditCards;
    }

    /**
     *This method will validate the Credit card object.
     * @param creditCard this param is a request body from endpoint
     * @return ValidationResult returns a boolean value (valid request: true; invalid request: false) with object if it is valid.
     * invalid result will return boolean value false with error code and description
     */
    public ValidationResult validate(CreditCard creditCard) {
        CreditCard validatedCreditCard = null;

        for (Validator validator: this.validators) {
            ValidationResult validationResult = validator.validate(creditCard);

            if (validator instanceof CreditCardParameterValidation) {
                validatedCreditCard = validationResult.getCreditCard();
            }
            if (! validationResult.isValid()) {
                return validationResult;
            }
        }
        return new ValidationResult(true, validatedCreditCard);
    }

}



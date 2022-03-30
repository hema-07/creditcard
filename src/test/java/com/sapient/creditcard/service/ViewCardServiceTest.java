package com.sapient.creditcard.service;

import com.sapient.creditcard.entity.CreditCard;
import com.sapient.creditcard.exception.CreditCardApplicationException;
import com.sapient.creditcard.modal.ErrorResponse;
import com.sapient.creditcard.repository.CreditCardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewCardServiceTest {

    @Autowired
    private ViewCardService viewCardService;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @Test
    public void getAllCreditCardDetails_success_scenario() throws ErrorResponse, CreditCardApplicationException {
        CreditCard card = CreditCard.builder()
                .cardNumber(61789372994L)
                .name("Hema")
                .limit(new BigDecimal("12949835.00"))
                .build();

        List<CreditCard> allCreditCardDetails = new ArrayList<>();
        allCreditCardDetails.add(card);

        Mockito.when((List<CreditCard>) creditCardRepository.findAll()).thenReturn(allCreditCardDetails);
        List<CreditCard> actualList = viewCardService.getAllCreditCardDetails();
        assertEquals(1, actualList.size());
    }

}
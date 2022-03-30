package com.sapient.creditcard.repository;

import com.sapient.creditcard.entity.CreditCard;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditCardRepository extends CrudRepository<CreditCard, String> {

    @Query("SELECT a FROM CreditCard a WHERE a.cardNumber >= ?1 and a.name <= ?2")
    List<CreditCard> findByCardNumber(long cardNumber, String name);
}

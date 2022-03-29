package com.sapient.creditcard.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "CREDIT_CARD")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard implements Serializable {

    @Id
    @Column(name = "CREDIT_ACCOUNT_ID")
    private String creditAccountId;

    @Column(name = "CREDIT_CARD_NUMBER")
    private BigInteger cardNumber;

    @Column(name = "CREDIT_CARD_HOLDER_NAME")
    private String name;

    @Column(name = "CREDIT_LIMIT")
    private BigDecimal limit;

    @Column(name = "CREDIT_BALANCE")
    private BigDecimal balance;

}

package com.sapient.creditcard.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreditCardRequest {

    @JsonProperty("CardNumber")
    private long cardNumber;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Limit")
    private BigDecimal limit;

}

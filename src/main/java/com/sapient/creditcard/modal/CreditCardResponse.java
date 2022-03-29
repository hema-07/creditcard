package com.sapient.creditcard.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sapient.creditcard.entity.CreditCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.net.http.HttpHeaders;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardResponse {

    @JsonProperty("Message")
    private String message;

    @JsonProperty("CreditCard Details")
    private List<CreditCard> body;

}

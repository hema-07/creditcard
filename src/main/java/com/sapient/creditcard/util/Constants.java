package com.sapient.creditcard.util;

public class Constants {

    public static final String existingCard = "CC_ERROR_1001";
    public static final String existingCardDescription = "Card is already present, Please enter new card details";

    public static final String cardNumberNotValid = "CC_ERROR_1002";
    public static final String cardNumberNotValidDescription = "Credit card number is not valid, Please enter new credit card number";

    public static final String noDataFromDB = "CC_ERROR_1003";
    public static final String noDataFromDBDetails = "There is no data in DB, Please add new credit card details to view";

    public static final String missingCreditCardNumber = "CC_ERROR_1004";
    public static final String missingCreditCardNumberDetails = "Missing Credit card number";

    public static final String mismatchlengthCreditCardNumber = "CC_ERROR_1005";
    public static final String mismatchlengthCreditCardNumberDetails = "Credit card number is larger than usual length";

    public static final String missingCreditCardHolderName = "CC_ERROR_1006";
    public static final String missingCreditCardHolderNameDetails = "Missing Credit card holder name";

    public static final String missingCreditCardLimit = "CC_ERROR_1007";
    public static final String missingCreditCardLimitDetails = "Missing Credit card limit";

    public static final String  invalidRequestParam = "CC_ERROR_1008";
    public static final String invalidRequestParamDetails = "Bad Request, Invalid request parameter :";

    public static final String  jdbcException = "CC_ERROR_1009";
    public static final String jdbcExceptionDetails = "JDBC connection exception occured :";

    public static final String  servletBindingError = "CC_ERROR_1010";
    public static final String servletBindingErrorDetails = "Invalid Body details :";

    public static final String mismatchLengthCreditCardHolderName = "CC_ERROR_1011";
    public static final String mismatchLengthCreditCardHolderNameDetails = "Mismatch length in column name, should be less than 50";

    public static final String mismatchlengthCreditCardLimit = "CC_ERROR_1012";
    public static final String mismatchlengthCreditCardLimitDetails = "Mismatch length in column limit, should be less than 25";

    public static final String authorization = "Authorization";

}

package com.sapient.creditcard.util;

public class Constants {

    public static final String EXISTING_CARD = "CC_ERROR_1001";
    public static final String EXISTING_CARD_DESC = "Card is already present, Please enter new card details";

    public static final String CARD_NUMBER_NOT_VALID = "CC_ERROR_1002";
    public static final String CARD_NUMBER_NOT_VALID_DESC = "Credit card number is not valid, Please enter new credit card number";

    public static final String NO_DATA_FROM_DB = "CC_ERROR_1003";
    public static final String NO_DATA_FROM_DB_DESC = "There is no data in DB, Please add new credit card details to view";

    public static final String MISSING_CARD_NUMBER = "CC_ERROR_1004";
    public static final String MISSING_CARD_NUMBER_DESC = "Missing Credit card number";

    public static final String MISMATCH_LENGTH_CARD_NUMBER = "CC_ERROR_1005";
    public static final String MISMATCH_LENGTH_CARD_NUMBER_DESC = "Credit card number is larger than usual length";

    public static final String MISSING_LENGTH_CARD_HOLDER_NAME = "CC_ERROR_1006";
    public static final String MISSING_LENGTH_CARD_HOLDER_NAME_DESC = "Missing Credit card holder name";

    public static final String MISSING_CARD_LIMIT = "CC_ERROR_1007";
    public static final String MISSING_CARD_LIMIT_DESC = "Missing Credit card limit";

    public static final String  INVALID_REQUEST_PARAM = "CC_ERROR_1008";
    public static final String INVALID_REQUEST_PARAM_DESC = "Bad Request, Invalid request parameter :";

    public static final String  JDBC_EXCEPTION = "CC_ERROR_1009";
    public static final String JDBC_EXCEPTION_DESC = "JDBC connection exception occured :";

    public static final String  SERVLET_BINDING_ERROR = "CC_ERROR_1010";
    public static final String SERVLET_BINDING_ERROR_DESC = "Invalid Body details :";

    public static final String MISMATCH_LENGTH_CARD_HOLDER_NAME = "CC_ERROR_1011";
    public static final String MISMATCH_LENGTH_CARD_HOLDER_NAME_DESC = "Mismatch length in column name, should be less than 50";

    public static final String MISMATCH_CARD_LIMIT = "CC_ERROR_1012";
    public static final String MISMATCH_CARD_LIMIT_DESC = "Mismatch length in column limit, should be less than 25";

    private Constants(){}
}

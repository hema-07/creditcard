package com.sapient.creditcard.exception;

import com.sapient.creditcard.modal.CreditCardResponse;
import com.sapient.creditcard.modal.ErrorResponse;
import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import static com.sapient.creditcard.util.Constants.*;

@ControllerAdvice
@RestController
public class CustomResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMsg = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(INVALID_REQUEST_PARAM)
                .errorDescription(INVALID_REQUEST_PARAM_DESC.concat(errorMsg))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(SERVLET_BINDING_ERROR)
                .errorDescription(SERVLET_BINDING_ERROR_DESC)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value= {JDBCException.class, JDBCConnectionException.class})
    public final ResponseEntity<Object> handleEJDBCException(JDBCException ex, WebRequest request) {
        String errorMsg = ex.getMessage();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(JDBC_EXCEPTION)
                .errorDescription(JDBC_EXCEPTION_DESC.concat(errorMsg))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {ErrorResponse.class})
    public final ResponseEntity<Object> handleErrorResponse(ErrorResponse ex, WebRequest request) {
        CreditCardResponse creditCardResponse = CreditCardResponse.builder()
                .message(ex.getErrorCode().concat(ex.getErrorDescription()))
                .build();
        logger.debug("ErrorResponse {}",ex);
        return new ResponseEntity<>(creditCardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value= {CreditCardApplicationException.class})
    public final ResponseEntity<Object> handleCreditCardApplicationException(Exception ex, WebRequest request) {
        String errorMsg = ex.getMessage();
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}


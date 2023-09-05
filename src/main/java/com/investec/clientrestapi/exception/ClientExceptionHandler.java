package com.investec.clientrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {
    @ExceptionHandler(InvalidIdNumberException.class)
    public final ResponseEntity<String> invalidIdHandler(InvalidIdNumberException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DupilcateMobileNumberException.class)
    public final ResponseEntity<String> dupilcateMobileNumberHandler(DupilcateMobileNumberException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MandotoryFieldsException.class)
    public final ResponseEntity<String> mandotoryFieldsHandler(MandotoryFieldsException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public final ResponseEntity<String> clientNotFoundHandler(ClientNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}

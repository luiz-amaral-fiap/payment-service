package br.com.fiap.paymentservice.exceptionHandler;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({EmptyResultDataException.class})
    protected ResponseEntity<Object> handleEmptyResultDataException(EmptyResultDataException ex,WebRequest webRequest){
        List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("Data Not Found Exception"));
        return handleExceptionInternal(ex,errors,new HttpHeaders(), HttpStatus.NOT_FOUND,webRequest);
    }

    @ExceptionHandler({InvalidDataException.class})
    protected ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex,WebRequest webRequest){
        List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage("Invalid Data Exception"));
        return handleExceptionInternal(ex,errors,new HttpHeaders(), HttpStatus.BAD_REQUEST,webRequest);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest) {
        String error =ex.getMessage();
        List<ErrorMessage> errors = Collections.singletonList(new ErrorMessage(error));
        return handleExceptionInternal(ex,errors,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,webRequest);
    }

}

package com.gmail.exceptionhandler;

import com.gmail.exception.ErrorDetails;
import com.gmail.exception.NoMailFound;
import com.gmail.exception.UserAlreadyExistException;
import com.gmail.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionalHandler {

    @ExceptionHandler(NoMailFound.class)
    public ResponseEntity<ErrorDetails> noMailFound(NoMailFound noMailFound, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.NO_CONTENT.value(),
                request.getDescription(false),
                noMailFound.getMessage()
        );


        return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> noHandler(NoHandlerFoundException noHandler, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Please enter valid URL",
                request.getDescription(false)
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorDetails> httpRequestMethodException(HttpRequestMethodNotSupportedException exception, WebRequest request){

        ErrorDetails errorDetail = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDetails> missingServletRequestParameterException(MissingServletRequestParameterException exception, WebRequest request){

        ErrorDetails errorDetail = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> myMANVExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {

        ErrorDetails err = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                methodArgumentNotValidException.getMessage()
        );

        return new ResponseEntity<>(err,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> generalException(Exception exception, WebRequest request){

        ErrorDetails errorDetail = new ErrorDetails(
                LocalDateTime.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                exception.getMessage(),
                request.getDescription(false)
        );

        return new ResponseEntity<ErrorDetails>(errorDetail, HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFound(UserNotFoundException userNotFoundException,WebRequest webRequest){
    	
    	ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(), HttpStatus.NOT_FOUND.value() ,"Not Found" ,userNotFoundException.getMessage());
    	
    	return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.NOT_FOUND);
    	
    }
    
    
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> userAlreadyExistException(UserAlreadyExistException userAlreadyExistException,WebRequest webRequest){
    	
    	ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(), HttpStatus.CONFLICT.value() ,"User Already Existed with this Email id" ,userAlreadyExistException.getMessage());
    	return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.CONFLICT);
    }
}


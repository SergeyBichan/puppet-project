package com.aston.frontendpracticeservice.handler;

import com.aston.frontendpracticeservice.domain.response.NotSimpleMessage;
import com.aston.frontendpracticeservice.domain.response.SimpleMessage;
import com.aston.frontendpracticeservice.exception.AuthException;
import com.aston.frontendpracticeservice.exception.UserNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected SimpleMessage handleMethodArgumentNotValid(IllegalArgumentException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(InvalidMediaTypeException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected SimpleMessage handleMethodArgumentNotValid(InvalidMediaTypeException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected SimpleMessage handleUserNotFoundException(UserNotFoundException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected SimpleMessage handleAuthException(AuthException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected SimpleMessage handleJwtException(JwtException exception) {
        return new SimpleMessage(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<NotSimpleMessage> handleMethodArgumentNotValid(MethodArgumentTypeMismatchException exception) {
        NotSimpleMessage notSimpleMessage = NotSimpleMessage.builder()
                .httpCode(HttpStatus.BAD_REQUEST.toString())
                .message(exception.getCause().getMessage())
                .details(
                        Map.of(
                                exception.getName(),
                                exception.getValue().toString())
                )
                .build();

        return new ResponseEntity<>(notSimpleMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected SimpleMessage handleNoResourceFoundException(NoResourceFoundException exception) {
        return new SimpleMessage(exception.getDetailMessageCode());
    }
}

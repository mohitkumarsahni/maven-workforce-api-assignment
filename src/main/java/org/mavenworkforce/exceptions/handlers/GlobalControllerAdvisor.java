package org.mavenworkforce.exceptions.handlers;

import org.mavenworkforce.exceptions.UserAlreadyExistException;
import org.mavenworkforce.exceptions.UserNotFoundException;
import org.mavenworkforce.pojos.responses.error.ErrorData;
import org.mavenworkforce.pojos.responses.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvisor {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> badCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(new ErrorResponse(new ErrorData(ex.getMessage(), "BAD_CREDENTIALS"), Instant.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> userNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(new ErrorData(ex.getMessage(), "USER_NOT_FOUND"), Instant.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> userAlreadyExistException(UserAlreadyExistException ex) {
        return new ResponseEntity<>(new ErrorResponse(new ErrorData(ex.getMessage(), "USER_ALREADY_EXIST"), Instant.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        return new ResponseEntity<>(new ErrorResponse(new ErrorData(details.get(0), "BAD_REQUEST"), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> accessDeniedException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(new ErrorData(ex.getLocalizedMessage(), "UNAUTHORIZED_ACCESS"), Instant.now()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> accessDeniedException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new ErrorResponse(new ErrorData("Request Body is missing.", "BAD_REQUEST"), Instant.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> internalServerException(Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(new ErrorData(ex.getLocalizedMessage(), "INTERNAL_SERVER_ERROR"), Instant.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

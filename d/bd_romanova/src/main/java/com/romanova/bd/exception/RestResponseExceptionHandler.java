package com.romanova.bd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> badRequest(Exception ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();
        Map<String, String> errors = Map.of("HttpStatus", status.name(),
                "message", message, "advice", "проверьте ограничения типа данных, PK или FK");
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedException(Exception ex){
        HttpStatus status = HttpStatus.FORBIDDEN;
        String message = ex.getMessage();
        Map<String, String> errors = Map.of("HttpStatus", status.name(), "message", message);
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

}

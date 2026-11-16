package br.com.samuel.manutencao.ExceptionHandler;

import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketResourceNotFoundException;
import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketResourceNullException;
import br.com.samuel.manutencao.ExceptionHandler.exceptions.TicketStatusException;
import br.com.samuel.manutencao.ExceptionHandler.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(TicketResourceNullException.class)
    public ResponseEntity<ResponseException> handleResourceNullException(Exception ex, WebRequest request){
        ResponseException response = new ResponseException(
                new Date(),
                request.getDescription(false),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketResourceNotFoundException.class)
    public ResponseEntity<ResponseException> handleResourceNotFoundException(Exception ex, WebRequest request){
        ResponseException response = new ResponseException(
                new Date(),
                request.getDescription(false),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TicketStatusException.class)
    public ResponseEntity<ResponseException> handleStatusException(Exception ex, WebRequest request){
        ResponseException response = new ResponseException(
                new Date(),
                request.getDescription(false),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseException> handleResourceGeneralException(Exception ex, WebRequest request){
        ResponseException response = new ResponseException(
                new Date(),
                request.getDescription(false),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

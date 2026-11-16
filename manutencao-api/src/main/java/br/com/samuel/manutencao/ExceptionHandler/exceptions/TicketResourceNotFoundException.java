package br.com.samuel.manutencao.ExceptionHandler.exceptions;

public class TicketResourceNotFoundException extends RuntimeException {
    public TicketResourceNotFoundException(String message) {
        super(message);
    }
}

package br.com.samuel.manutencao.ExceptionHandler.exceptions;

public class TicketResourceNullException extends RuntimeException {
    public TicketResourceNullException(String message) {
        super(message);
    }
}

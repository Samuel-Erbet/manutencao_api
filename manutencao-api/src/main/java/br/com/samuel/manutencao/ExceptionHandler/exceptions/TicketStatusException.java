package br.com.samuel.manutencao.ExceptionHandler.exceptions;

public class TicketStatusException extends RuntimeException {
    public TicketStatusException(String message) {
        super(message);
    }
}

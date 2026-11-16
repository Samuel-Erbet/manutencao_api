package br.com.samuel.manutencao.ExceptionHandler.response;

import java.util.Date;

public record ResponseException(
    Date timestamp,
    String details,
    String message
) {
}

package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception;

public class TokenInvalidoException extends RuntimeException{

    public TokenInvalidoException(String message) {
        super(message);
    }
}

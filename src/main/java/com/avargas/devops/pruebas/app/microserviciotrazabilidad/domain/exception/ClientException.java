package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.exception;

public class ClientException extends RuntimeException{

    public ClientException(String message) {
        super(message);
    }
}

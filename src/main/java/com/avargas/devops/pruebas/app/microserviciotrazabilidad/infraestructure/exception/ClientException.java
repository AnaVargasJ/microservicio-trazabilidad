package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception;

public class ClientException extends RuntimeException{

    public ClientException(String message) {
        super(message);
    }
}

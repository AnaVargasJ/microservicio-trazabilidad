package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception;

public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(String message) {
        super(message);
    }
}

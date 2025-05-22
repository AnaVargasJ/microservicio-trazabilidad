package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception;

public class JsonParseClientException extends RuntimeException {
    public JsonParseClientException(String message, Throwable cause) {
        super(message, cause);
    }
}

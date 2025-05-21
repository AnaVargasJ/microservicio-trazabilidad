package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Exception {

    ERROR_CONSULTAR_USUARIO("Error al consultar el usuario con correo: ");
    private final String message;
}

package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.controllerAdvisor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensajeError {

    ERROR_VALIDACION("Error al validar el token");

    private final String message;
}

package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.controllerAdvisor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensajeError {

    ERROR_VALIDACION("Error al validar el token"),
    ERROR_PARSEO("Error de parseo"),
    ERROR_DATA("No existe data a coonsultar");

    private final String message;
}

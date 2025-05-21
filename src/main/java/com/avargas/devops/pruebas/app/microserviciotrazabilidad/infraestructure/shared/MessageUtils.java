package com.avargas.devops.pruebas.app.microserviciotrazabilidad.infraestructure.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageUtils {

    MESSAGE_TOKEN("Acceso denegado: el token es requerido o inválido."),
    MESAGE_EXITOSO("Se crea trazaexitosamente");

    private final String message;


}

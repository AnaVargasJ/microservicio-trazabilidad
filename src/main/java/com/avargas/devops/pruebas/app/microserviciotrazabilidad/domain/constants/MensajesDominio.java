package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MensajesDominio {
    MENSAJE_SIN_TRAZABILIDAD ("No existe trazabilidad para el pedido "),
    MENSAJE_RANKING_GENERADO ("No existe ranking generado");

    private final String message;


}

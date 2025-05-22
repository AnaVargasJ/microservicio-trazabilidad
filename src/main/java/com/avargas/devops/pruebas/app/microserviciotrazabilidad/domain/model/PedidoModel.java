package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoModel {

    private Long idPedido;
    private LocalDateTime fecha;
    private String estado;
    private Long idCliente;
    private Long idChef;
    private String correoEmpleado;
    private Long tiempoEnSegundos;
}


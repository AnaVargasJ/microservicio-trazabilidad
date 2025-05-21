package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrazabilidadModel {
    private Long id;
    private Long idPedido;
    private String idCliente;
    private String correoCliente;
    private Date fecha;
    private String estadoAnterior;
    private String estadoNuevo;
    private Long idEmpleado;
    private String correoEmpleado;

}

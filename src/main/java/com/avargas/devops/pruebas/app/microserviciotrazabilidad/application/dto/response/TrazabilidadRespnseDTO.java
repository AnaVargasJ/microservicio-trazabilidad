package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrazabilidadRespnseDTO {

    private String idTrazabilidad;

    private Long idPedido;
    private String idCliente;
    private String correoCliente;
    private Date fecha;
    private String estadoAnterior;
    private String estadoNuevo;
    private Long idEmpleado;
    private String correoEmpleado;
}

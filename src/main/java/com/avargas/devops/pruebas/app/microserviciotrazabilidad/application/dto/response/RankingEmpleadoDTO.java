package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankingEmpleadoDTO {
    private Long idEmpleado;
    private String correoEmpleado;
    private Double promedioSegundos;
    private Long totalPedidos;
}
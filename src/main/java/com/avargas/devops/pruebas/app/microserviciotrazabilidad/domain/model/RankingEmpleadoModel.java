package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RankingEmpleadoModel {

    private Long idEmpleado;
    private String correoEmpleado;
    private Double promedioSegundos;
    private Long totalPedidos;
}

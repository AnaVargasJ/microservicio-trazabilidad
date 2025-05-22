package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.PedidoResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.RankingEmpleadoDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.TrazabilidadRespnseDTO;

import java.util.List;

public interface IHandlerTrazabilidad {

    void guardarTrazabilidad(TrazabilidadRequestDTO requestDTO);

    List<TrazabilidadRespnseDTO> consultarTrazabilidadPedido(Long idPedido, String idCliente);

    List<PedidoResponseDTO> calcularTiempoPorPedido(Long idRestaurante, String token);
     List<RankingEmpleadoDTO> rankingPorEmpleado(Long idRestaurante, String token);

}
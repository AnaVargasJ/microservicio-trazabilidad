package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler.impl;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.PedidoResponseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.RankingEmpleadoDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.response.TrazabilidadRespnseDTO;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler.IHandlerTrazabilidad;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper.IPedidoResponseMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper.IRankingEmpleadoMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper.ITrazabilidadRequestMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.mapper.ITrazabilidadResponseMapper;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.IClientAdapter;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api.ITrazaServicePort;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.PedidoModel;
import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrazabilidadHandler implements IHandlerTrazabilidad {

    private final ITrazaServicePort trazaServicePort;
    private final ITrazabilidadRequestMapper requestMapper;
    private final ITrazabilidadResponseMapper responseMapper;
    private final IPedidoResponseMapper pedidoResponseMapper;
    private final IClientAdapter clientAdapter;
    private final IRankingEmpleadoMapper rankingEmpleadoMapper;

    @Override
    public void guardarTrazabilidad(TrazabilidadRequestDTO requestDTO) {
        TrazabilidadModel trazabilidadModel = requestMapper.toTrazabilidadDTOToModel(requestDTO);
        trazaServicePort.saveTrazabilidad(trazabilidadModel);
    }

    @Override
    public List<TrazabilidadRespnseDTO> consultarTrazabilidadPedido(Long idPedido, String idCliente) {
        return responseMapper.toTrazabilidadResponse(trazaServicePort.consultarTrazabilidadPedido(idPedido,idCliente));
    }

    @Override
    public List<PedidoResponseDTO> calcularTiempoPorPedido(Long idRestaurante, String token) {
       List<PedidoModel> pedidosPorRestaurante = clientAdapter.obtenerPedidosPorRestaurante(token, idRestaurante);
        return pedidoResponseMapper.toResponseDtoList(trazaServicePort.calcularTiempoPorPedido(pedidosPorRestaurante));
    }

    @Override
    public List<RankingEmpleadoDTO> rankingPorEmpleado(Long idRestaurante, String token) {
        List<PedidoModel> pedidosPorRestaurante = clientAdapter.obtenerPedidosPorRestaurante(token, idRestaurante);
        return rankingEmpleadoMapper.toDtoList(trazaServicePort.calcularRankingPorEmpleado(pedidosPorRestaurante));
    }
}

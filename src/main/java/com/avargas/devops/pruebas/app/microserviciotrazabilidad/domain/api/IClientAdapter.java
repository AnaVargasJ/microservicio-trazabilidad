package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.PedidoModel;

import java.util.List;

public interface IClientAdapter {

    List<PedidoModel> obtenerPedidosPorRestaurante(String token, Long idRestaurante);
}

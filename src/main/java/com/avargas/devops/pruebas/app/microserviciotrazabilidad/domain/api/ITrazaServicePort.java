package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;

import java.util.List;

public interface ITrazaServicePort {

    void saveTrazabilidad(TrazabilidadModel trazabilidadModel);

    List<TrazabilidadModel> consultarTrazabilidadPedido(Long idPedido, String idCliente);
}

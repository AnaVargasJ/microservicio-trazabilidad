package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;

import java.util.List;

public interface ITrazaPersistencePort {

    TrazabilidadModel guardarTrazabilidad(TrazabilidadModel trazabilidadModel);
    List<TrazabilidadModel> consultarTrazabilidadPedido(Long idPedido, String idCliente);


}

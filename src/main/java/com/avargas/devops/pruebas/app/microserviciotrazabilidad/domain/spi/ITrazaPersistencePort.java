package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.spi;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;

public interface ITrazaPersistencePort {

    TrazabilidadModel guardarTrazabilidad(TrazabilidadModel trazabilidadModel);
}

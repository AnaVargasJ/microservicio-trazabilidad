package com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.api;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.domain.model.TrazabilidadModel;

public interface ITrazaServicePort {

    void saveTrazabilidad(TrazabilidadModel trazabilidadModel);
}

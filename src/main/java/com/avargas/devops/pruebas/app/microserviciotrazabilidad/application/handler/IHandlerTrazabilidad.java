package com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.handler;

import com.avargas.devops.pruebas.app.microserviciotrazabilidad.application.dto.request.TrazabilidadRequestDTO;

public interface IHandlerTrazabilidad {

    void guardarTrazabilidad(TrazabilidadRequestDTO requestDTO);
}
